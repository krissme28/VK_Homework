package com.example.vk_homework.applist.data

import com.example.vk_homework.applist.domain.AppListCategory

data class AppListItemDto(
    val name: String,
    val appCategory: AppListCategory,
    val iconUrl: String,
    val description: String
)