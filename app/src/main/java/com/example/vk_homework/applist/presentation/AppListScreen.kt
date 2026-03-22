package com.example.vk_homework.applist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.vk_homework.ui.theme.RuStoreBlue

@Composable
fun AppListScreen(
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppListViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.showSnackbarEvent.collect { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = RuStoreBlue,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { AppListTopBar() }
    ) { innerPadding ->

        when (val state = uiState) {
            is AppListState.Loading -> {
                Box(Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }
            }

            is AppListState.Error -> {
                Box(Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                    Text(text = state.error ?: "Ошибка загрузки", color = Color.White)
                }
            }

            is AppListState.Content -> {
                AppListContent(
                    appList = state.appList,
                    innerPadding = innerPadding,
                    onClick = onClick,
                    onLogoClick = { app -> viewModel.onLogoClick(app.name) }
                )
            }
        }
    }
}
