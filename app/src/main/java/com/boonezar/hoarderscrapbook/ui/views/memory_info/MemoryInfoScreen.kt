package com.boonezar.hoarderscrapbook.ui.views.memory_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.ui.composables.ImageSlider
import com.boonezar.hoarderscrapbook.ui.composables.PrimaryButton
import com.boonezar.hoarderscrapbook.ui.composables.ScreenHeader
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.ui.theme.HoarderScrapbookTheme
import com.boonezar.hoarderscrapbook.ui.views.memory_info.MemoryInfoContract.*

@Composable
fun MemoryInfoScreen(
    viewState: androidx.compose.runtime.State<State>,
    onEvent: (event: Event) -> Unit
) {
    val state = viewState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        ScreenHeader(
            title = state.memory?.name ?: "",
            onBack = { onEvent(Event.OnBack) }
        )
        ImageSlider(images = state.images)
        Text(
           text = state.memory?.estimateDateOfMemory ?: ""
        )
        Text (
            text = state.memory?.description ?: ""
        )
        PrimaryButton(
            text = stringResource(id = R.string.edit_memory),
            onClick = { onEvent(Event.OnEdit) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MemoryInfoScreenPreview() {
    HoarderScrapbookTheme {
        val viewState = remember {
            mutableStateOf(
                State(
                    memory = Memory(
                        id = 1,
                        entryDate = "2024-01-15",
                        estimateDateOfMemory = "2020-06-01",
                        name = "Grandma's Teapot",
                        description = "The blue teapot that was always on the kitchen counter. She used it every morning."
                    ),
                    images = emptyList()
                )
            )
        }
        MemoryInfoScreen(viewState = viewState, onEvent = { })
    }
}