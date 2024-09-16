package com.boonezar.hoarderscrapbook.ui.views.memory_info

import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.ui.views.ViewEffect
import com.boonezar.hoarderscrapbook.ui.views.ViewEvent
import com.boonezar.hoarderscrapbook.ui.views.ViewState

class MemoryInfoContract {
    sealed class Event: ViewEvent {
        data object OnBack: Event()
        data object OnEdit: Event()
    }
    data class State(
        val memory: Memory?,
        val images: List<ImageUri>
    ): ViewState
    sealed class Effect: ViewEffect {
        data object ToPreviousScreen: Effect()
        data object ToEditMemoryScreen: Effect()
    }
}