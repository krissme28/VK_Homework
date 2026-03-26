package com.example.vk_homework

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vk_homework.appdetails.presentation.AppDetailsScreen
import com.example.vk_homework.appdetails.presentation.AppDetailsViewModel
import com.example.vk_homework.applist.presentation.AppListScreen
import com.example.vk_homework.applist.presentation.AppListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AppList.route
    ) {

        // --- ЭКРАН СПИСКА ---
        composable(Screen.AppList.route) {
            val viewModel: AppListViewModel = hiltViewModel()
            AppListScreen(
                viewModel = viewModel,
                onClick = { appId ->
                    navController.navigate(Screen.AppDetails.createRoute(appId))
                }
            )
        }

        // --- ЭКРАН ДЕТАЛЕЙ ПРИЛОЖЕНИЯ---
        composable(Screen.AppDetails.route) {
            val viewModel = hiltViewModel<AppDetailsViewModel>()
            AppDetailsScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object AppList : Screen("app_list")
    data object AppDetails : Screen("app_details/{id}") {
        fun createRoute(id: String) = "app_details/$id"
    }
}





