package com.boonezar.hoarderscrapbook.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.boonezar.hoarderscrapbook.ui.views.SharedViewModel
import com.boonezar.hoarderscrapbook.ui.views.add_edit_memory.AddEditMemoryDestination
import com.boonezar.hoarderscrapbook.ui.views.dashboard.DashboardDestination
import com.boonezar.hoarderscrapbook.ui.views.memories.MemoriesDestination
import com.boonezar.hoarderscrapbook.ui.views.memory_info.MemoryInfoDestination
import com.boonezar.hoarderscrapbook.ui.views.slideshow.SlideshowDestination

@Composable
fun NavGraph (
    navController: NavHostController,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.DASHBOARD.route
    ) {
        composable(route = Screens.DASHBOARD.route) {
            DashboardDestination(navController)
        }
        composable(route = Screens.SLIDESHOW.route) {
            SlideshowDestination(navController)
        }
        composable(route = Screens.MEMORIES.route) {
            MemoriesDestination(navController, sharedViewModel)
        }
        composable(route = Screens.MEMORY_INFO.route) {
            MemoryInfoDestination(navController, sharedViewModel)
        }
        composable(route = Screens.ADD_EDIT_MEMORY.route) {
            AddEditMemoryDestination(navController, sharedViewModel)
        }
    }
}