package com.boonezar.hoarderscrapbook.ui.views.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.boonezar.hoarderscrapbook.ui.views.about.AboutContract.*

@Composable
fun AboutScreen(
    viewState: androidx.compose.runtime.State<State>,
    onEvent: (event: Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        ScreenHeader(
            title = stringResource(id = R.string.about),
            onBack = { onEvent(Event.OnBack) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.about_body),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    HoarderScrapbookTheme {
        val viewState = remember { mutableStateOf(State()) }
        AboutScreen(viewState = viewState, onEvent = { })
    }
}
