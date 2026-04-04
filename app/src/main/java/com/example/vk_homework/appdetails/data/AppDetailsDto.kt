package com.example.vk_homework.appdetails.data

import com.google.gson.annotations.SerializedName

data class AppDetailsDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val rawName: String?,
    val developerName: String?,
    val categoryId: String?,
    val categoryTitle: String?,
    @SerializedName("age_limit") val ageLimit: Int?,
    @SerializedName("size_mb") val sizeInMb: Float?,
    @SerializedName("iconUrl") val iconPath: String?,
    @SerializedName("screenshots") val screenshots: List<String>?,
    @SerializedName("description") val fullDescription: String?
)