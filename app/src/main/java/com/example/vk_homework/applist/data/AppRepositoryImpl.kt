package com.example.vk_homework.applist.data

import com.example.vk_homework.applist.domain.AppListItem
import com.example.vk_homework.applist.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val api: CatalogApi) : AppRepository {
    override suspend fun getAppList(): List<AppListItem> {
        return api.getCatalog().toDomainList()
    }

    override suspend fun getAppDetails(id: String): AppListItem {
        return api.getAppDetails(id).toDomain()
    }
}