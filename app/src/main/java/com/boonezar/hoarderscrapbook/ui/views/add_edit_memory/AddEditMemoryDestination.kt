package com.boonezar.hoarderscrapbook.ui.views.add_edit_memory

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.boonezar.hoarderscrapbook.ui.navigation.Screens
import com.boonezar.hoarderscrapbook.ui.views.add_edit_memory.AddEditMemoryContract.Effect.*
import com.boonezar.hoarderscrapbook.ui.views.SharedViewModel
import com.boonezar.hoarderscrapbook.ui.views.VIEW_EFFECTS_KEY
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditMemoryDestination(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val addEditMemoryViewModel =
        hiltViewModel<AddEditMemoryViewModel, AddEditMemoryViewModelFactory> { factory ->
        factory.create(sharedViewModel.memoryRepository)
    }
    AddEditMemoryScreen(
        viewState = addEditMemoryViewModel.viewState,
        onEvent = { addEditMemoryViewModel.setEvent(it) }
    )

    LaunchedEffect(VIEW_EFFECTS_KEY) {
        addEditMemoryViewModel.effect.onEach { effect ->
            when (effect) {
                ToPreviousScreen -> navController.popBackStack()
                ToMemoriesScreen -> navController.popBackStack(Screens.MEMORIES.route, inclusive = false)
            }
        }.collect()
    }
}