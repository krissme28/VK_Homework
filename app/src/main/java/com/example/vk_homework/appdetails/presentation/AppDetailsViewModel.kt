package com.example.vk_homework.appdetails.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.vk_homework.appdetails.domain.AppDetails
import com.example.vk_homework.appdetails.domain.AppDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val appId: String? = savedStateHandle["id"]

    var appData by mutableStateOf<AppDetails?>(null)
        private set

    init {
        appData = repository.getAppDetails()
    }
}