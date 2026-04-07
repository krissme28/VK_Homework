package com.example.vk_homework.appdetails.presentation

import androidx.compose.runtime.Immutable
import com.example.vk_homework.appdetails.domain.AppDetails

@Immutable
data class AppDetailsState(
    val appDetails: AppDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)