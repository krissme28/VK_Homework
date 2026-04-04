package com.example.vk_homework.appdetails.data

import com.example.vk_homework.appdetails.domain.AppCategory
import com.example.vk_homework.appdetails.domain.AppDetails
import com.example.vk_homework.appdetails.domain.Developer
import javax.inject.Inject

fun AppDetailsDto.toDomain(): AppDetails {
    return AppDetails(
        name = this.rawName ?: "",
        developer = Developer(name = this.developerName ?: "Неизвестен"),
        appCategory = AppCategory(this.id ?: "", this.categoryTitle ?: "Разное"),
        ageRating = this.ageLimit ?: 0,
        size = this.sizeInMb ?: 0f,
        iconUrl = this.iconPath ?: "",
        screenshotUrlList = this.screenshots ?: emptyList(),
        description = this.fullDescription ?: "",
        isInWishlist = false
    )
}

fun List<AppDetailsDto>.toDomainList(): List<AppDetails> {
    return this.map { it.toDomain() }
}

class AppDetailsMapper @Inject constructor() {
    fun mapToDomain(dto: AppDetailsDto): AppDetails {
        return dto.toDomain()
    }
}