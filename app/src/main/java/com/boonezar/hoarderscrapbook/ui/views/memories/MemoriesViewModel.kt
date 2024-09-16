package com.boonezar.hoarderscrapbook.ui.views.memories

import androidx.lifecycle.viewModelScope
import com.boonezar.hoarderscrapbook.models.MemoryDataWrapper
import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import com.boonezar.hoarderscrapbook.ui.views.BaseViewModel
import com.boonezar.hoarderscrapbook.ui.views.memories.MemoriesContract.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MemoriesViewModelFactory::class)
class MemoriesViewModel @AssistedInject constructor(
    @Assisted private val memoryRepository: MemoryRepository
): BaseViewModel<Event, State, Effect>() {
    init {
        viewModelScope.launch {
            memoryRepository.getMemories().flowOn(Dispatchers.IO).collect { memories ->
                setState { copy(memories = memories) }
            }
        }
    }

    override fun setInitialState() = State(emptyList())

    override fun handleEvents(event: Event) {
        when (event) {
            Event.OnBack -> setEffect(Effect.ToPreviousScreen)
            Event.OnAddMemory -> setEffect(Effect.ToAddMemoryScreen)
            is Event.OnMemory -> {
                memoryRepository.saveDataWrapper(MemoryDataWrapper(selectedMemoryId = event.id))
                setEffect(Effect.ToMemoryInfoScreen)
            }
        }
    }
}