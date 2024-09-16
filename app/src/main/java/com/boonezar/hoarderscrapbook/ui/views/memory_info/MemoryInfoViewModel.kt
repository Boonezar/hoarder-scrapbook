package com.boonezar.hoarderscrapbook.ui.views.memory_info

import androidx.lifecycle.viewModelScope
import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import com.boonezar.hoarderscrapbook.ui.views.BaseViewModel
import com.boonezar.hoarderscrapbook.ui.views.memory_info.MemoryInfoContract.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MemoryInfoViewModelFactory::class)
class MemoryInfoViewModel @AssistedInject constructor(
    @Assisted private val memoryRepository: MemoryRepository
): BaseViewModel<Event, State, Effect>() {
    init {
        memoryRepository.getDataWrapper().selectedMemoryId?.let { id ->
            viewModelScope.launch {
                memoryRepository.getMemory(id).flowOn(Dispatchers.IO).collect { memory ->
                    setState { copy(memory = memory) }
                }
            }
            viewModelScope.launch {
                memoryRepository.getImagesForMemory(id).flowOn(Dispatchers.IO).collect { images ->
                    setState { copy(images = images) }
                }
            }
        }
    }
    override fun setInitialState() = State(null, emptyList())

    override fun handleEvents(event: Event) {
        when (event) {
            Event.OnBack -> {
                memoryRepository.clearDataWrapper()
                setEffect(Effect.ToPreviousScreen)
            }
            Event.OnEdit -> setEffect(Effect.ToEditMemoryScreen)
        }
    }
}