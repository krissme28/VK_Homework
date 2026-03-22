package com.example.vk_homework.appdetails.data

import com.example.vk_homework.appdetails.domain.AppCategory
import com.example.vk_homework.appdetails.domain.AppDetails
import com.example.vk_homework.appdetails.domain.Developer

fun AppDetailsDto.toDomain(): AppDetails {
    return AppDetails(
        name = this.rawName,
        developer = Developer(name = this.developerName),
        appCategory = AppCategory(this.id, this.categoryTitle),
        ageRating = this.ageLimit,
        size = this.sizeInMb,
        iconUrl = this.iconPath,
        screenshotUrlList = this.screenshots,
        description = this.fullDescription
    )
}

fun List<AppDetailsDto>.toDomainList(): List<AppDetails> {
    return this.map { it.toDomain() }
}