package com.example.vk_homework.appdetails

import androidx.annotation.StringRes
import com.example.vk_homework.R

enum class Category(@StringRes val labelRes: Int) {
    FINANCE(R.string.category_finance),
    GAME(R.string.category_game),
    TRANSPORT(R.string.category_transport),
    APP(R.string.category_app),
    COMMUNICATION(R.string.category_communication),
    PURCHASES(R.string.category_purchases)
}