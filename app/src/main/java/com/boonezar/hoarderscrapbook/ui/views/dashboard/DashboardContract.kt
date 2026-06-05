package com.boonezar.hoarderscrapbook.ui.views.dashboard

import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.models.MemoryWithImages
import com.boonezar.hoarderscrapbook.ui.views.ViewEffect
import com.boonezar.hoarderscrapbook.ui.views.ViewEvent
import com.boonezar.hoarderscrapbook.ui.views.ViewState

class DashboardContract {
    sealed class Event: ViewEvent {
        data object OnSlideshow: Event()
        data object OnMemories: Event()
        data object OnAddMemory: Event()
        data object OnAbout: Event()
    }
    data class State(
        val memories: List<MemoryWithImages> = emptyList(),
        val currentIndex: Int = 0
    ): ViewState
    sealed class Effect: ViewEffect {
        data object ToSlideshowScreen: Effect()
        data object ToMemoriesScreen: Effect()
        data object ToAddMemoryScreen: Effect()
        data object ToAboutScreen: Effect()
    }
}