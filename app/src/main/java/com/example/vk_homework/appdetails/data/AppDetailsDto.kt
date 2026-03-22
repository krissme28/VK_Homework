package com.example.vk_homework.appdetails.data

data class AppDetailsDto(
    val id: Int,
    val rawName: String,
    val developerName: String,
    val categoryId: Int,
    val categoryTitle: String,
    val ageLimit: Int,
    val sizeInMb: Float,
    val iconPath: String,
    val screenshots: List<String>,
    val fullDescription: String
)