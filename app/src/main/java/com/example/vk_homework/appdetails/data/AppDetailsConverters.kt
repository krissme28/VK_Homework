package com.example.vk_homework.appdetails.data

import androidx.room.TypeConverter

class AppDetailsConverters {
    @TypeConverter
    fun fromList(value: List<String>): String = value.joinToString(",")

    @TypeConverter
    fun toList(value: String): List<String> = value.split(",").filter { it.isNotBlank() }
}