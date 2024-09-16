package com.boonezar.hoarderscrapbook.ui.views.memories

import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.ui.views.ViewEffect
import com.boonezar.hoarderscrapbook.ui.views.ViewEvent
import com.boonezar.hoarderscrapbook.ui.views.ViewState

class MemoriesContract {
    sealed class Event: ViewEvent {
        data object OnBack: Event()
        data object OnAddMemory: Event()
        data class OnMemory(val id: Int): Event()
    }
    data class State(
        val memories: List<Memory>
    ): ViewState
    sealed class Effect: ViewEffect {
        data object ToPreviousScreen: Effect()
        data object ToAddMemoryScreen: Effect()
        data object ToMemoryInfoScreen: Effect()
    }
}