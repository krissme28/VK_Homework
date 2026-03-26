package com.example.vk_homework.applist.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.vk_homework.R
import com.example.vk_homework.applist.domain.AppListCategory

@StringRes
fun AppListCategory.getLabelRes(): Int {
    return when (this) {
        AppListCategory.FINANCE -> R.string.category_finance
        AppListCategory.GAME -> R.string.category_game
        AppListCategory.TRANSPORT -> R.string.category_transport
        AppListCategory.APP -> R.string.category_app
        AppListCategory.COMMUNICATION -> R.string.category_communication
        AppListCategory.HEALTH -> R .string.category_health
        AppListCategory.EDUCATION -> R.string.category_edu
        AppListCategory.SHOPPING -> R.string.category_purchases
        AppListCategory.PHOTO -> R.string.category_photo
        AppListCategory.PERFORMANCE -> R.string.category_performance
        AppListCategory.UTILITIES -> R.string.category_utilities
        AppListCategory.WEATHER -> R.string.category_weather
        AppListCategory.MUSIC -> R.string.category_music
        AppListCategory.BOOKS -> R.string.category_books
        AppListCategory.BUSINESS -> R.string.category_business
        AppListCategory.FOOD -> R.string.category_food
        AppListCategory.NEWS -> R.string.category_news
        AppListCategory.LIFESTYLE -> R.string.category_lifestyle
    }
}

@DrawableRes
fun AppListCategory.getPlaceholderRes(): Int {
    return when (this) {
        AppListCategory.GAME -> R.drawable.ic_game_placeholder
        else -> R.drawable.ic_default_app_icon // общая иконка "приложение"
    }
}