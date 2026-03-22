package com.example.vk_homework.di

import com.example.vk_homework.appdetails.data.AppDetailsRepositoryImpl
import com.example.vk_homework.appdetails.domain.AppDetailsRepository
import com.example.vk_homework.applist.data.AppRepositoryImpl
import com.example.vk_homework.applist.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

    @Binds
    abstract fun bindAppDetailsRepository(impl: AppDetailsRepositoryImpl): AppDetailsRepository
}