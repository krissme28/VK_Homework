package com.example.vk_homework.appdetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_homework.appdetails.domain.AppDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val appId: String? = savedStateHandle["id"]

    private val _uiState = MutableStateFlow(AppDetailsState())
    val uiState: StateFlow<AppDetailsState> = _uiState.asStateFlow()

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
                    _uiState.update { it.copy(appDetails = details) }
                }
        }
    }

    private fun fetchInitialData(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                repository.getAppDetails(id)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            repository.toggleWishlist(appId ?: return@launch)
        }
    }
}