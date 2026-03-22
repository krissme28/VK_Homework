package com.example.vk_homework.applist.presentation

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
        AppListCategory.PURCHASES -> R.string.category_purchases
    }
}