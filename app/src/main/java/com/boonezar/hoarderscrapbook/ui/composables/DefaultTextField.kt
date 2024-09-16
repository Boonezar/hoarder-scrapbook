package com.boonezar.hoarderscrapbook.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultTextField(
    value: String,
    label: String,
    onValueChange: (value: String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth()
    )
}