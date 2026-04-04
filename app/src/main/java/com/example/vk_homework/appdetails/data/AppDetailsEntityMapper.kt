package com.example.vk_homework.appdetails.data

import com.example.vk_homework.appdetails.domain.AppCategory
import com.example.vk_homework.appdetails.domain.AppDetails
import com.example.vk_homework.appdetails.domain.Developer
import javax.inject.Inject

class AppDetailsEntityMapper @Inject constructor() {

    fun mapToDomain(entity: AppDetailsEntity): AppDetails {
        return AppDetails(
            name = entity.name,
            developer = Developer(name = entity.developerName),
            appCategory = AppCategory(entity.id, entity.categoryTitle),
            ageRating = entity.ageLimit,
            size = entity.sizeInMb,
            iconUrl = entity.iconPath,
            screenshotUrlList = entity.screenshots,
            description = entity.fullDescription
        )
    }

    fun mapToEntity(dto: AppDetailsDto): AppDetailsEntity {
        return AppDetailsEntity(
            id = dto.id ?: "",
            name = dto.rawName ?: "",
            developerName = dto.developerName ?: "Неизвестен",
            categoryTitle = dto.categoryTitle ?: "Разное",
            ageLimit = dto.ageLimit ?: 0,
            sizeInMb = dto.sizeInMb ?: 0f,
            iconPath = dto.iconPath ?: "",
            screenshots = dto.screenshots ?: emptyList(),
            fullDescription = dto.fullDescription ?: ""
        )
    }
}