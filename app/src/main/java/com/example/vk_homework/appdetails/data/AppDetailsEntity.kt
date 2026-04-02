package com.example.vk_homework.appdetails.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_details")
data class AppDetailsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val developerName: String,
    val categoryTitle: String,
    val ageLimit: Int,
    val sizeInMb: Float,
    val iconPath: String,
    val screenshots: List<String>,
    val fullDescription: String
)