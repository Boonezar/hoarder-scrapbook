package com.boonezar.hoarderscrapbook.ui.views.dashboard

import androidx.lifecycle.viewModelScope
import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import com.boonezar.hoarderscrapbook.ui.views.BaseViewModel
import com.boonezar.hoarderscrapbook.ui.views.dashboard.DashboardContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val memoryRepository: MemoryRepository
): BaseViewModel<Event, State, Effect>() {

    init {
        viewModelScope.launch {
            memoryRepository.getAllMemoriesWithImages()
                .flowOn(Dispatchers.IO)
                .collect { memories ->
                    setState { copy(memories = memories) }
                }
        }
    }

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.OnSlideshow -> setEffect(Effect.ToSlideshowScreen)
                Event.OnMemories -> setEffect(Effect.ToMemoriesScreen)
                Event.OnAddMemory -> setEffect(Effect.ToAddMemoryScreen)
                Event.OnAbout -> setEffect(Effect.ToAboutScreen)
            }
        }
    }
}
