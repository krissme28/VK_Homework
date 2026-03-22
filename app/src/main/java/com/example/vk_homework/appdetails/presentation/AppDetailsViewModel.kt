package com.example.vk_homework.appdetails.presentation

import androidx.lifecycle.ViewModel
import com.example.vk_homework.appdetails.domain.AppDetailsRepository

class AppDetailsViewModel(private val repository: AppDetailsRepository) : ViewModel() {
    val appData = repository.getAppDetails()
}