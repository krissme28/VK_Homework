package com.example.vk_homework.appdetails.data

import androidx.room.TypeConverter

class AppDetailsConverters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",").filter { it.isNotEmpty() }
    }
}