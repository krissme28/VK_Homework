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
        println("LOG: ViewModel создана, appId = $appId")
        viewModelScope.launch {
            if (appId == null) {
                android.util.Log.e("MY_APP_DEBUG", "Ошибка: appId is NULL!")
                return@launch
            }
            appId?.let { id ->
                try {
                    println("LOG: Начинаю загрузку для ID: $id")
                    val result = repository.getAppDetails(id)
                    println("LOG: Данные успешно получены: ${result.name}")
                    appData = result
                } catch (e: Exception) {
                    android.util.Log.e("MY_APP_DEBUG", "Ошибка загрузки: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }
}