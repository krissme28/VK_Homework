package com.example.vk_homework.appdetails.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_homework.appdetails.domain.AppDetails
import com.example.vk_homework.appdetails.domain.AppDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        appId?.let { id ->
            observeAppDetails(id)
            fetchInitialData(id)
        }
    }

    private fun observeAppDetails(id: String) {
        viewModelScope.launch {
            repository.observeAppDetails(id)
                .collect { details ->
                    android.util.Log.d("MY_APP_DEBUG", "Flow получил данные: isInWishlist = ${details.isInWishlist}")
                    appData = details
                }
        }
    }

    private fun fetchInitialData(id: String) {
        viewModelScope.launch {
            try {
                repository.getAppDetails(id)
            } catch (e: Exception) {
                android.util.Log.e("MY_APP_DEBUG", "Ошибка загрузки: ${e.message}")
            }
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            repository.toggleWishlist(appId ?: return@launch)
        }
    }
}