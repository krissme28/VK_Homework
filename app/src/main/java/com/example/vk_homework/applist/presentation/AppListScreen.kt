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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vk_homework.ui.theme.RuStoreBlue
import com.example.vk_homework.ui.theme.VK_HomeworkTheme

@Composable
fun AppListScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppListViewModel = viewModel()
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

@Preview(showBackground = true)
@Composable
fun AppListScreenPreview() {
    VK_HomeworkTheme() {
        AppListScreen(
            onClick = {}
        )
    }
}
