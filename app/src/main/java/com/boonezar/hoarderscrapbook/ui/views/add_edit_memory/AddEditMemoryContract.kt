package com.boonezar.hoarderscrapbook.ui.views.add_edit_memory

import android.net.Uri
import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.ui.views.ViewEffect
import com.boonezar.hoarderscrapbook.ui.views.ViewEvent
import com.boonezar.hoarderscrapbook.ui.views.ViewState

class AddEditMemoryContract {
    sealed class Event: ViewEvent {
        data object OnBack: Event()
        data class OnNameChange(val name: String): Event()
        data class OnDescriptionChange(val description: String): Event()
        data object OnDateTextFieldTap: Event()
        data object OnDismissDatePicker: Event()
        data class OnConfirmDatePicker(val date: String): Event()
        data object OnSave: Event()
        data object OnDelete: Event()
        data class OnCameraPictureAccepted(val uri: Uri): Event()
        data class OnFileSelectorAccepted(val uri: Uri): Event()
    }
    data class State(
        val isEditMode: Boolean,
        val id: Int?,
        val name: String,
        val description: String,
        val estimateDateOfMemory: String,
        val entryDate: String,
        val images: List<ImageUri>,
        val showDatePicker: Boolean,
        val isSaveButtonEnabled: Boolean
    ): ViewState
    sealed class Effect: ViewEffect {
        data object ToPreviousScreen: Effect()
        data object ToMemoriesScreen: Effect()
    }
}