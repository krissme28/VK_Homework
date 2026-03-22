package com.example.vk_homework.applist.domain

data class AppListItem(
    val name: String,
    val appCategory: AppListCategory,
    val iconUrl: String,
    val description: String,
)