package com.boonezar.hoarderscrapbook.ui.views.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.boonezar.hoarderscrapbook.ui.navigation.Screens.*
import com.boonezar.hoarderscrapbook.ui.views.dashboard.DashboardContract.Effect.*
import com.boonezar.hoarderscrapbook.ui.views.VIEW_EFFECTS_KEY
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun DashboardDestination(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    DashboardScreen(
        viewState = viewModel.viewState,
        onEvent = { viewModel.setEvent(it) }
    )

    LaunchedEffect(VIEW_EFFECTS_KEY) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                ToSlideshowScreen -> navController.navigate(SLIDESHOW.route)
                ToMemoriesScreen -> navController.navigate(MEMORIES.route)
                ToAddMemoryScreen -> navController.navigate(ADD_EDIT_MEMORY.route)
            }
        }.collect()
    }
}