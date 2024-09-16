package com.boonezar.hoarderscrapbook.ui.views.memories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.boonezar.hoarderscrapbook.ui.navigation.Screens.*
import com.boonezar.hoarderscrapbook.ui.views.SharedViewModel
import com.boonezar.hoarderscrapbook.ui.views.memories.MemoriesContract.Effect.*
import com.boonezar.hoarderscrapbook.ui.views.VIEW_EFFECTS_KEY
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MemoriesDestination(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val memoriesViewModel = hiltViewModel<MemoriesViewModel, MemoriesViewModelFactory> { factory ->
        factory.create(sharedViewModel.memoryRepository)
    }
    MemoriesScreen(
        viewState = memoriesViewModel.viewState,
        onEvent = { memoriesViewModel.setEvent(it) }
    )

    LaunchedEffect(VIEW_EFFECTS_KEY) {
        memoriesViewModel.effect.onEach { effect ->
            when (effect) {
                ToPreviousScreen -> navController.popBackStack()
                ToAddMemoryScreen -> navController.navigate(ADD_EDIT_MEMORY.route)
                ToMemoryInfoScreen -> navController.navigate(MEMORY_INFO.route)
            }
        }.collect()
    }
}