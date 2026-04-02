package com.example.vk_homework.di

import dagger.Module
import dagger.Provides
import androidx.room.Room
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.example.vk_homework.appdetails.data.AppDatabase
import com.example.vk_homework.appdetails.data.AppDetailsDao
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideAppDetailsDao(db: AppDatabase): AppDetailsDao {
        return db.appDetailsDao()
    }
}