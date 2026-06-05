package com.boonezar.hoarderscrapbook.ui.views.about

import com.boonezar.hoarderscrapbook.ui.views.ViewEffect
import com.boonezar.hoarderscrapbook.ui.views.ViewEvent
import com.boonezar.hoarderscrapbook.ui.views.ViewState

class AboutContract {
    sealed class Event : ViewEvent {
        data object OnBack : Event()
    }
    class State : ViewState
    sealed class Effect : ViewEffect {
        data object ToPreviousScreen : Effect()
    }
}
