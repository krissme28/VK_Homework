package com.example.vk_homework.applist

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
    val apps by viewModel.apps.collectAsState()
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
        AppListContent(
            apps,
            innerPadding,
            onClick,
            onLogoClick = { app -> viewModel.onLogoClick(app.name) }
        )
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
