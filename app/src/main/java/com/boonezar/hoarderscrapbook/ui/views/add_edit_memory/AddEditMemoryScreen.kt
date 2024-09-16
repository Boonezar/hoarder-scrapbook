package com.boonezar.hoarderscrapbook.ui.views.add_edit_memory

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.providers.ComposeFileProvider
import com.boonezar.hoarderscrapbook.ui.composables.DefaultTextField
import com.boonezar.hoarderscrapbook.ui.composables.ImageSlider
import com.boonezar.hoarderscrapbook.ui.composables.PrimaryButton
import com.boonezar.hoarderscrapbook.ui.composables.ScreenHeader
import com.boonezar.hoarderscrapbook.ui.composables.TextFieldWithDatePicker
import com.boonezar.hoarderscrapbook.ui.views.add_edit_memory.AddEditMemoryContract.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditMemoryScreen(
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
            title = stringResource(
                id = if(state.isEditMode) R.string.edit_memory else R.string.add_memory
            ),
            onBack = { onEvent(Event.OnBack) }
        )
        DefaultTextField(
            value = state.name,
            label = stringResource(id = R.string.name),
            onValueChange = { onEvent(Event.OnNameChange(it)) }
        )
        ImagesSection(state, onEvent)
        TextFieldWithDatePicker(
            value = state.estimateDateOfMemory,
            label = stringResource(id = R.string.est_date_of_memory),
            showDialog = state.showDatePicker,
            onConfirm = { onEvent(Event.OnConfirmDatePicker(it)) },
            onDismiss = { onEvent(Event.OnDismissDatePicker) },
            onTextFieldTap = { onEvent(Event.OnDateTextFieldTap) }
        )
        DefaultTextField(
            value = state.description,
            label = stringResource(id = R.string.description),
            onValueChange = { onEvent(Event.OnDescriptionChange(it)) }
        )
        PrimaryButton(
            text = stringResource(id = R.string.save),
            isEnabled = state.isSaveButtonEnabled,
            onClick = { onEvent(Event.OnSave) }
        )
        if (state.isEditMode) {
            PrimaryButton(
                text = stringResource(id = R.string.delete),
                onClick = { onEvent(Event.OnDelete) }
            )
        }

    }
}

@Composable
fun ImagesSection(state: State, onEvent: (event: Event) -> Unit) {
    val context = LocalContext.current
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    val fileSelectionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                onEvent(Event.OnFileSelectorAccepted(uri))
            }
        }
    )
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && imageUri != null) {
                onEvent(Event.OnCameraPictureAccepted(imageUri!!))
            }
        }
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        ImageSlider(images = state.images)
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryButton(
                text = stringResource(id = R.string.select_image),
                onClick = { fileSelectionLauncher.launch("image/*") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            PrimaryButton(
                text = stringResource(id = R.string.take_photo),
                onClick = {
                    val uri = ComposeFileProvider.getCacheImageUri(context)
                    imageUri = uri
                    cameraLauncher.launch(uri)
                }
            )
        }
    }
}