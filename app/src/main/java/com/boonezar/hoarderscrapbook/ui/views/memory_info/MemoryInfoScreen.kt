package com.boonezar.hoarderscrapbook.ui.views.memory_info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.ui.composables.ImageSlider
import com.boonezar.hoarderscrapbook.ui.composables.PrimaryButton
import com.boonezar.hoarderscrapbook.ui.composables.ScreenHeader
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