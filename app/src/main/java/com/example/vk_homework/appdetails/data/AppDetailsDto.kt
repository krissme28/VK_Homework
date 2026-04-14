package com.example.vk_homework.appdetails.data

import com.google.gson.annotations.SerializedName

data class AppDetailsDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val rawName: String? = null,
    @SerializedName("developer") val developerName: String? = null,
    @SerializedName("ageRating") val ageLimit: Int? = null,
    @SerializedName("size") val sizeInMb: Float? = null,
    val categoryTitle: String? = null,
    @SerializedName("iconUrl") val iconPath: String? = null,
    @SerializedName("screenshotUrlList") val screenshots: List<String>? = null,
    @SerializedName("description") val fullDescription: String? = null
)