package com.boonezar.hoarderscrapbook.ui.views.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.ui.composables.PrimaryButton
import com.boonezar.hoarderscrapbook.ui.composables.ScreenHeader
import com.boonezar.hoarderscrapbook.ui.theme.HoarderScrapbookTheme
import com.boonezar.hoarderscrapbook.ui.views.dashboard.DashboardContract.Event
import com.boonezar.hoarderscrapbook.ui.views.dashboard.DashboardContract.State

@Composable
fun DashboardScreen(
    viewState: androidx.compose.runtime.State<State>,
    onEvent: (event: Event) -> Unit
) {
    val state = viewState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            title = stringResource(id = R.string.hoarders_scrapbook),
            includeBackButton = false
        )
        Box(
            modifier = Modifier
                .size(248.dp)
                .clickable { onEvent(Event.OnSlideshow) },
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.tv_24),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .height(145.dp)
                    .width(188.dp)
                    .padding(bottom = 20.dp)
                    .background(MaterialTheme.colorScheme.inverseSurface),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = "Tap to expand!",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        PrimaryButton(
            text = stringResource(id = R.string.view_memories),
            onClick = { onEvent(Event.OnMemories) }
        )
        PrimaryButton(
            text = stringResource(id = R.string.add_memory),
            onClick = { onEvent(Event.OnAddMemory) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    HoarderScrapbookTheme {
        val viewState = remember { mutableStateOf(State()) }
        DashboardScreen(viewState = viewState, onEvent = { /* no-op */ })
    }
}