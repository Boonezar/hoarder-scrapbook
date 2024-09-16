package com.boonezar.hoarderscrapbook.ui.views.dashboard

import androidx.lifecycle.viewModelScope
import com.boonezar.hoarderscrapbook.ui.views.BaseViewModel
import com.boonezar.hoarderscrapbook.ui.views.dashboard.DashboardContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(): BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()
    override fun handleEvents(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.OnSlideshow -> setEffect(Effect.ToSlideshowScreen)
                Event.OnMemories -> setEffect(Effect.ToMemoriesScreen)
                Event.OnAddMemory -> setEffect(Effect.ToAddMemoryScreen)
            }
        }
    }
}