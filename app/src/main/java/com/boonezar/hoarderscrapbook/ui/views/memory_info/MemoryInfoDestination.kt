package com.boonezar.hoarderscrapbook.ui.views.memory_info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.boonezar.hoarderscrapbook.ui.navigation.Screens.ADD_EDIT_MEMORY
import com.boonezar.hoarderscrapbook.ui.views.SharedViewModel
import com.boonezar.hoarderscrapbook.ui.views.VIEW_EFFECTS_KEY
import com.boonezar.hoarderscrapbook.ui.views.memories.MemoriesViewModelFactory
import com.boonezar.hoarderscrapbook.ui.views.memory_info.MemoryInfoContract.Effect.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MemoryInfoDestination(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val memoryInfoViewModel = hiltViewModel<MemoryInfoViewModel, MemoryInfoViewModelFactory>{ factory ->
        factory.create(sharedViewModel.memoryRepository)
    }

    MemoryInfoScreen(
        viewState = memoryInfoViewModel.viewState,
        onEvent = { memoryInfoViewModel.setEvent(it) }
    )

    LaunchedEffect(VIEW_EFFECTS_KEY) {
        memoryInfoViewModel.effect.onEach { effect ->
            when (effect) {
                ToPreviousScreen -> navController.popBackStack()
                ToEditMemoryScreen -> navController.navigate(ADD_EDIT_MEMORY.route)
            }
        }.collect()
    }
}