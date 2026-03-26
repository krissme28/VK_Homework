package com.example.vk_homework.applist.domain

interface AppRepository {
    suspend fun getAppList(): List<AppListItem>
    suspend fun getAppDetails(id: String): AppListItem
}