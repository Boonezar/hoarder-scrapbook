package com.boonezar.hoarderscrapbook.ui.views.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.models.MemoryWithImages
import com.boonezar.hoarderscrapbook.ui.composables.PrimaryButton
import com.boonezar.hoarderscrapbook.ui.theme.DarkWalnut
import com.boonezar.hoarderscrapbook.ui.theme.HoarderScrapbookTheme
import com.boonezar.hoarderscrapbook.ui.theme.PaleGold
import com.boonezar.hoarderscrapbook.ui.theme.SoftAmber
import com.boonezar.hoarderscrapbook.ui.views.dashboard.DashboardContract.Event
import com.boonezar.hoarderscrapbook.ui.views.dashboard.DashboardContract.State
import kotlinx.coroutines.delay

@Composable
fun DashboardScreen(
    viewState: androidx.compose.runtime.State<State>,
    onEvent: (event: Event) -> Unit
) {
    val state = viewState.value

    val allImages = remember(state.memories) {
        state.memories.flatMap { memory ->
            memory.images.map { image -> memory to image }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Spacer(modifier = Modifier.size(40.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.hoarders),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.scrapbook),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
            IconButton(
                onClick = { onEvent(Event.OnAbout) },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = stringResource(id = R.string.about),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        PhotoFrame(
            allImages = allImages,
            onClick = { onEvent(Event.OnSlideshow) }
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            PrimaryButton(
                text = stringResource(id = R.string.view_memories),
                onClick = { onEvent(Event.OnMemories) },
                modifier = Modifier.weight(1f)
            )
            PrimaryButton(
                text = stringResource(id = R.string.add_memory),
                onClick = { onEvent(Event.OnAddMemory) },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PhotoFrame(
    allImages: List<Pair<MemoryWithImages, ImageUri>>,
    onClick: () -> Unit
) {
    val frameShape = RoundedCornerShape(4.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, frameShape)
            .clip(frameShape)
            .border(12.dp, SoftAmber, frameShape)
            .border(14.dp, DarkWalnut, frameShape)
            .background(PaleGold)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (allImages.isNotEmpty()) {
                val pagerState = rememberPagerState(pageCount = { allImages.size })

                LaunchedEffect(pagerState, allImages.size) {
                    if (allImages.size > 1) {
                        while (true) {
                            delay(5000)
                            val nextPage = (pagerState.currentPage + 1) % allImages.size
                            pagerState.animateScrollToPage(nextPage)
                        }
                    }
                }

                val isPreview = LocalInspectionMode.current

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 4f)
                        .padding(14.dp)
                ) { page ->
                    val (memory, image) = allImages[page]
                    AsyncImage(
                        model = image.uri,
                        contentDescription = memory.memory.name,
                        contentScale = ContentScale.Crop,
                        placeholder = if (isPreview) painterResource(R.drawable.ic_launcher_foreground) else null,
                        modifier = Modifier
                            .fillMaxSize()
                            .then(
                                if (isPreview) Modifier.background(MaterialTheme.colorScheme.primary)
                                else Modifier
                            )
                    )
                }

                val currentMemoryName = allImages.getOrNull(pagerState.currentPage)
                    ?.first?.memory?.name ?: ""

                Text(
                    text = currentMemoryName,
                    style = MaterialTheme.typography.titleMedium,
                    color = DarkWalnut,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 4.dp, bottom = 20.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 4f)
                        .padding(14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.no_memories_yet),
                        style = MaterialTheme.typography.bodyLarge,
                        color = DarkWalnut,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(24.dp)
                    )
                }

                Text(
                    text = "",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 4.dp, bottom = 20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    HoarderScrapbookTheme {
        val viewState = remember {
            mutableStateOf(
                State(
                    memories = listOf(
                        MemoryWithImages(
                            memory = Memory(1, "2024-01-15", "2020-06-01", "Grandma's Teapot", "The blue teapot"),
                            images = listOf(ImageUri(1, 1, ""))
                        )
                    )
                )
            )
        }
        DashboardScreen(viewState = viewState, onEvent = { })
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenEmptyPreview() {
    HoarderScrapbookTheme {
        val viewState = remember { mutableStateOf(State()) }
        DashboardScreen(viewState = viewState, onEvent = { })
    }
}