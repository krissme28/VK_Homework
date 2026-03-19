package com.example.vk_homework.applist.presentation

import com.example.vk_homework.applist.domain.AppListItem

sealed class AppListState {
    data object Loading : AppListState()

    data class Content(
        val appList: List<AppListItem> = emptyList(),
        val isRefreshing: Boolean = false
    ) : AppListState()

    data class Error(
        val message: String? = null,
        val error: String?
    ) : AppListState()
}