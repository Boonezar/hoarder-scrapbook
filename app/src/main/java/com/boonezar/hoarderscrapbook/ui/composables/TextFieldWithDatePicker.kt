package com.boonezar.hoarderscrapbook.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.ui.UiUtils.convertDateToMillis
import com.boonezar.hoarderscrapbook.ui.UiUtils.convertMillisToDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithDatePicker(
    value: String,
    label: String,
    showDialog: Boolean,
    onConfirm: (date: String) -> Unit,
    onDismiss: () -> Unit,
    onTextFieldTap: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = if (value.isEmpty()) null else convertDateToMillis(value)
    )
    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Text(
                    text = stringResource(id = R.string.ok),
                    modifier = Modifier.clickable {
                        // selectedDateMillis returns the day before, so adding 1 day of millis
                        val oneDayOfMillis = 86400000
                        val millis = datePickerState.selectedDateMillis?.let { it + oneDayOfMillis }
                        val date = convertMillisToDate(millis)
                        onConfirm(date)
                    }
                )
            },
            dismissButton = {
                Text(
                    text = stringResource(id = R.string.cancel),
                    modifier = Modifier.clickable { onDismiss() }
                )
            },
            content = { DatePicker(datePickerState) }
        )
    }
    val source = remember { MutableInteractionSource() }
    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.mipmap.ic_calendar),
                contentDescription = "calendar"
            )
        },
        modifier = Modifier.fillMaxWidth(),
        interactionSource = source
    )
    if (source.collectIsPressedAsState().value) {
        onTextFieldTap()
    }
}