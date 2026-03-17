package com.example.vk_homework.applist

import com.example.vk_homework.appdetails.AppCategory
data class AppListItem(
    val name: String,
    val appCategory: AppCategory,
    val iconUrl: String,
    val description: String,
)