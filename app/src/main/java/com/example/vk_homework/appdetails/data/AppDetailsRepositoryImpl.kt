package com.example.vk_homework.appdetails.data

import com.example.vk_homework.appdetails.domain.AppDetails
import com.example.vk_homework.appdetails.domain.AppDetailsRepository
import com.example.vk_homework.applist.data.CatalogApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val catalogApi: CatalogApi,
    private val dao: AppDetailsDao,
    private val entityMapper: AppDetailsEntityMapper,
    private val detailsMapper: AppDetailsMapper
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): AppDetails {
        val entity = dao.getAppDetails(id).firstOrNull()

        return if (entity != null) {
            entityMapper.mapToDomain(entity)
        } else {
            val dto = catalogApi.getAppDetails(id)
            withContext(Dispatchers.IO) {
                val newEntity = entityMapper.mapToEntity(dto)
                dao.insertAppDetails(newEntity)
            }
            detailsMapper.mapToDomain(dto)
        }
    }
}