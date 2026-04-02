package com.example.vk_homework.appdetails.domain

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): AppDetails
}