package com.example.vk_homework.appdetails

data class AppDetails(
    val name: String,
    val developer: String,
    val appCategory: AppCategory,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>,
    val description: String,
)