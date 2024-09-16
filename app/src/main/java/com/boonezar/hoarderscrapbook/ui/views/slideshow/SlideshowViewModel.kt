package com.boonezar.hoarderscrapbook.ui.views.slideshow

import androidx.lifecycle.viewModelScope
import com.boonezar.hoarderscrapbook.ui.views.BaseViewModel
import com.boonezar.hoarderscrapbook.ui.views.slideshow.SlideshowContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(): BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()
    override fun handleEvents(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.OnBack -> setEffect(Effect.ToPreviousScreen)
            }
        }
    }
}