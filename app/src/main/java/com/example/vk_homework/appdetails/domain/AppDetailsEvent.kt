package com.example.vk_homework.appdetails.domain

sealed interface AppDetailsEvent {
    data object UnderDevelopment : AppDetailsEvent
}