package com.boonezar.hoarderscrapbook.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.ui.theme.HoarderScrapbookTheme

@Composable
fun ScreenHeader(
    title: String,
    includeBackButton: Boolean = true,
    onBack: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()){
        if (includeBackButton) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back_24),
                contentDescription = stringResource(id = R.string.back_button),
                modifier = Modifier.size(32.dp).clickable { onBack() }
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenHeaderPreview() {
    HoarderScrapbookTheme {
        ScreenHeader(title = "Example Title", onBack = {})
    }
}