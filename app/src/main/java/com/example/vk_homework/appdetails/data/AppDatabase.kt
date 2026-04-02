package com.example.vk_homework.appdetails.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AppDetailsEntity::class], version = 1)
@TypeConverters(AppDetailsConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDetailsDao(): AppDetailsDao
}