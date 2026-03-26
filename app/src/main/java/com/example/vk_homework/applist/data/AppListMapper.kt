package com.example.vk_homework.applist.data

import com.example.vk_homework.applist.domain.AppListCategory
import com.example.vk_homework.applist.domain.AppListItem

fun AppListItemDto.toDomain(): AppListItem {
    return AppListItem(
        id = this.id,
        name = this.name,
        appCategory = mapToCategory(this.category),
        iconUrl = this.iconUrl,
        description = this.description
    )
}

fun List<AppListItemDto>.toDomainList(): List<AppListItem> {
    return this.map { it.toDomain() }
}

private fun mapToCategory(category: String): AppListCategory {
    return when (category.lowercase()) {
        "производительность" -> AppListCategory.PERFORMANCE
        "здоровье и фитнес", "здоровье" -> AppListCategory.HEALTH
        "инструменты", "полезные инструменты", "utilities" -> AppListCategory.UTILITIES
        "погода" -> AppListCategory.WEATHER
        "музыка" -> AppListCategory.MUSIC
        "книги" -> AppListCategory.BOOKS
        "бизнес", "бизнес-сервисы" -> AppListCategory.BUSINESS
        "еда и напитки", "еда" -> AppListCategory.FOOD
        "новости", "новости и события" -> AppListCategory.NEWS
        "финансы" -> AppListCategory.FINANCE
        "развлечения", "игры" -> AppListCategory.GAME
        "транспорт и навигация" -> AppListCategory.TRANSPORT
        "общение", "социальные" -> AppListCategory.COMMUNICATION
        "образование" -> AppListCategory.EDUCATION
        "образ жизни" -> AppListCategory.LIFESTYLE
        "фото и видео" -> AppListCategory.PHOTO
        "щоппинг" -> AppListCategory.SHOPPING
        else -> AppListCategory.APP
    }
}