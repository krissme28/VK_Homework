package com.example.vk_homework.applist.domain

interface AppRepository {
    fun getAppList(): List<AppListItem>
}