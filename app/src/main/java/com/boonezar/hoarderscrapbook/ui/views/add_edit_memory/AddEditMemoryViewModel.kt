package com.boonezar.hoarderscrapbook.ui.views.add_edit_memory

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import com.boonezar.hoarderscrapbook.ui.UiUtils.getDateStringOfToday
import com.boonezar.hoarderscrapbook.ui.views.add_edit_memory.AddEditMemoryContract.*
import com.boonezar.hoarderscrapbook.ui.views.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel(assistedFactory = AddEditMemoryViewModelFactory::class)
class AddEditMemoryViewModel @AssistedInject constructor(
    @Assisted private val memoryRepository: MemoryRepository
): BaseViewModel<Event, State, Effect>() {
    init {
        val id = memoryRepository.getDataWrapper().selectedMemoryId
        if (id != null) {
            viewModelScope.launch {
                memoryRepository.getMemory(id).filterNotNull().flowOn(Dispatchers.IO).collect { memory ->
                    println("SAM: getMemoryWithImages result: $memory")
                    setState { copy(
                        isEditMode = true,
                        id = id,
                        name = memory.name,
                        description = memory.description,
                        estimateDateOfMemory = memory.estimateDateOfMemory,
                        entryDate = memory.entryDate,
                        images = images
                    ) }
                    updateSaveButton()
                }
            }
            viewModelScope.launch {
                memoryRepository.getImagesForMemory(id).filterNotNull().flowOn(Dispatchers.IO).collect { images ->
                    setState { copy(images = images) }
                }
            }
        } else {
            setState { copy(entryDate = getDateStringOfToday()) }
        }
    }
    override fun setInitialState() = State(
        isEditMode = false,
        id = null,
        name = "",
        description = "",
        estimateDateOfMemory = "",
        entryDate = "",
        images = emptyList(),
        showDatePicker = false,
        isSaveButtonEnabled = false
    )

    override fun handleEvents(event: Event) {
        when(event) {
            Event.OnBack -> toPreviousScreen()
            is Event.OnNameChange -> onNameChange(event.name)
            is Event.OnDescriptionChange -> setState { copy(description = event.description) }
            Event.OnDateTextFieldTap -> setState { copy(showDatePicker = true) }
            Event.OnDismissDatePicker -> setState { copy(showDatePicker = false) }
            is Event.OnConfirmDatePicker -> setState { copy(
                showDatePicker = false,
                estimateDateOfMemory = event.date
            ) }
            Event.OnSave -> onSave()
            Event.OnDelete -> onDelete()
            is Event.OnCameraPictureAccepted -> addUriToImagesList(event.uri)
            is Event.OnFileSelectorAccepted -> addUriToImagesList(event.uri)
        }
    }

    private fun onNameChange(name: String) {
        setState { copy(name = name) }
        updateSaveButton()
    }

    private fun updateSaveButton() = setState { copy(
        isSaveButtonEnabled = viewState.value.name.isNotEmpty()
    ) }

    private fun onSave() {
        viewModelScope.launch(Dispatchers.IO) {
            saveMemory()
            saveImageUris()
            toPreviousScreen()
        }
    }

    private fun onDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            memoryRepository.deleteMemory(Memory(viewState.value.id ?: 0, "", "", "", ""))
            setEffect(Effect.ToMemoriesScreen)
        }
    }

    private fun addUriToImagesList(uri: Uri) {
        val newImages = viewState.value.images.toMutableList()
        newImages.add(ImageUri(
            id = 0,
            memoryId= 0,
            uri = uri.toString()
        ))
        setState { copy(images = newImages) }
    }

    private fun getUpdatedMemory(): Memory {
        val state = viewState.value
        return Memory(
            id = state.id ?: 0,
            name = state.name,
            estimateDateOfMemory = state.estimateDateOfMemory,
            entryDate = state.entryDate,
            description = state.description
        )
    }

    private suspend fun saveMemory() {
        val memory = getUpdatedMemory()
        if (viewState.value.isEditMode) {
            memoryRepository.updateMemory(memory)
        } else {
            val id = memoryRepository.insertMemory(memory)
            setState { copy(id = id) }
        }
    }

    private suspend fun saveImageUris() {
        viewState.value.images.forEach { imageUri ->
            if (imageUri.id == 0) {
                memoryRepository.insertImageUri(imageUri.copy(memoryId = viewState.value.id ?: 0))
            }
        }
    }

    private fun toPreviousScreen() = setEffect(Effect.ToPreviousScreen)
}