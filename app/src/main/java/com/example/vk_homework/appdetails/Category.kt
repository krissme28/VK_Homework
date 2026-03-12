package com.example.vk_homework.appdetails

enum class Category {
    FINANCE,
    APP,
    GAME,
    TRANSPORT,
    COMMUNICATION,
    PURCHASES
}

fun Category.toUiString(): String {
    return when (this) {
        Category.FINANCE -> "Финансы"
        Category.GAME -> "Развлечения"
        Category.TRANSPORT -> "Транспорт и Навигация"
        Category.APP -> "Приложение"
        Category.COMMUNICATION -> "Общение"
        Category.PURCHASES -> "Покупки"
    }
}