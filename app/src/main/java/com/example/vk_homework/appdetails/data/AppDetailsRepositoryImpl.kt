package com.example.vk_homework.appdetails.data

import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.firstOrNull
import com.example.vk_homework.appdetails.domain.AppDetails
import com.example.vk_homework.appdetails.domain.AppDetailsRepository
import com.example.vk_homework.applist.data.CatalogApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val catalogApi: CatalogApi,
    private val dao: AppDetailsDao,
    private val entityMapper: AppDetailsEntityMapper,
    private val detailsMapper: AppDetailsMapper
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): AppDetails {
        val entity = dao.getAppDetailsFlow(id).firstOrNull()

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
    override fun observeAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetailsFlow(id)
            .filterNotNull()
            .map { entityMapper.mapToDomain(it) }
    }

    override suspend fun toggleWishlist(id: String) {
        val currentEntity = dao.getAppDetailsFlow(id).firstOrNull()
        currentEntity?.let { entity ->
            val newStatus = !entity.isInWishlist
            withContext(Dispatchers.IO) {
                dao.updateWishlistStatus(id, newStatus)
            }
            android.util.Log.d("MY_APP_DEBUG", "Статус в БД изменен на: $newStatus")
        } ?: android.util.Log.e("MY_APP_DEBUG", "Ошибка: сущность не найдена в БД!")
    }
}