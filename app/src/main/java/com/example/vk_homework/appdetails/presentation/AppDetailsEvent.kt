package com.example.vk_homework.appdetails.presentation

sealed interface AppDetailsEvent {
    data object UnderDevelopment : AppDetailsEvent
}