package com.example.vk_homework.applist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_homework.applist.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<AppListState>(AppListState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _showSnackbarEvent = MutableSharedFlow<String>()
    val showSnackbarEvent = _showSnackbarEvent.asSharedFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            try {
                val data = repository.getAppList()
                _uiState.value = AppListState.Content(appList = data)
            } catch (e: Exception) {
                _uiState.value = AppListState.Error(error = e.localizedMessage)
            }
        }
    }

    fun onLogoClick(appName: String) {
        viewModelScope.launch {
            _showSnackbarEvent.emit("Нажато на логотип: $appName")
        }
    }
}