package com.example.vk_homework

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vk_homework.appdetails.data.AppDetailsRepositoryImpl
import com.example.vk_homework.appdetails.presentation.AppDetailsScreen
import com.example.vk_homework.appdetails.presentation.AppDetailsViewModel
import com.example.vk_homework.applist.data.AppRepositoryImpl
import com.example.vk_homework.applist.presentation.AppListScreen
import com.example.vk_homework.applist.presentation.AppListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AppList.route) {

        // --- ЭКРАН СПИСКА ---
        composable(Screen.AppList.route) {
            val repository = AppRepositoryImpl()
            val viewModel = remember { AppListViewModel(repository) }

            AppListScreen(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(Screen.AppDetails.route)
                }
            )
        }

        // --- ЭКРАН ДЕТАЛЕЙ ПРИЛОЖЕНИЯ---
        composable(Screen.AppDetails.route) {
            val repository = AppDetailsRepositoryImpl()
            val viewModel = remember { AppDetailsViewModel(repository) }

            AppDetailsScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object AppList: Screen("appList")
    data object AppDetails: Screen("appDetails")
}





