package com.example.vk_homework.applist

import com.example.vk_homework.appdetails.Category
data class AppListItem(
    val name: String,
    val category: Category,
    val iconUrl: String,
    val description: String,
)