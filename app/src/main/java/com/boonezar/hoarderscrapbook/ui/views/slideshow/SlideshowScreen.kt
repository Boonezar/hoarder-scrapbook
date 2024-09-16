package com.boonezar.hoarderscrapbook.ui.views.slideshow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.ui.composables.ScreenHeader
import com.boonezar.hoarderscrapbook.ui.theme.HoarderScrapbookTheme
import com.boonezar.hoarderscrapbook.ui.views.slideshow.SlideshowContract.*

@Composable
fun SlideshowScreen(
    viewState: androidx.compose.runtime.State<State>,
    onEvent: (event: Event) -> Unit
) {
    val state = viewState.value
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        ScreenHeader(
            title = stringResource(id = R.string.slideshow),
            onBack = { onEvent(Event.OnBack) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowScreenPreview() {
    HoarderScrapbookTheme {
        val viewState = remember { mutableStateOf(State()) }
        SlideshowScreen(viewState = viewState, onEvent = { /* no-op */ })
    }
}