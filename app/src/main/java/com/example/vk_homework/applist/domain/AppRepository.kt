package com.example.vk_homework.applist.domain

import com.example.vk_homework.appdetails.domain.AppDetails

interface AppRepository {
    suspend fun getAppList(): List<AppListItem>
    suspend fun getAppDetails(id: String): AppDetails
}