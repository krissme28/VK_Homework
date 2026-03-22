package com.example.vk_homework.applist.data

import com.example.vk_homework.applist.domain.AppListItem

fun AppListItemDto.toDomain(): AppListItem {
    return AppListItem(
        name = this.name,
        appCategory = this.appCategory,
        iconUrl = this.iconUrl,
        description = this.description
    )
}

fun List<AppListItemDto>.toDomainList(): List<AppListItem> {
    return this.map { it.toDomain() }
}