package com.example.vk_homework.appdetails.data

import com.google.gson.annotations.SerializedName

data class AppDetailsDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val rawName: String?,
    @SerializedName("developer") val developerName: String?,
    @SerializedName("ageRating") val ageLimit: Int?,
    @SerializedName("size") val sizeInMb: Float?,
    val categoryTitle: String?,
    @SerializedName("iconUrl") val iconPath: String?,
    @SerializedName("screenshotUrlList") val screenshots: List<String>?,
    @SerializedName("description") val fullDescription: String?
)