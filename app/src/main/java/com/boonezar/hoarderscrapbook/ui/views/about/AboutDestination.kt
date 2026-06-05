package com.boonezar.hoarderscrapbook.ui.views.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.boonezar.hoarderscrapbook.ui.views.about.AboutContract.Effect.ToPreviousScreen
import com.boonezar.hoarderscrapbook.ui.views.VIEW_EFFECTS_KEY
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun AboutDestination(
    navController: NavController,
    viewModel: AboutViewModel = hiltViewModel()
) {
    AboutScreen(
        viewState = viewModel.viewState,
        onEvent = { viewModel.setEvent(it) }
    )

    LaunchedEffect(VIEW_EFFECTS_KEY) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                ToPreviousScreen -> navController.popBackStack()
            }
        }.collect()
    }
}
