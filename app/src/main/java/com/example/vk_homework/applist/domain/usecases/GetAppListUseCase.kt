package com.example.vk_homework.applist.domain.usecases

import com.example.vk_homework.applist.domain.AppListItem
import com.example.vk_homework.applist.domain.AppRepository
import javax.inject.Inject

class GetAppListUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<AppListItem> {
        return repository.getAppList()
    }
}
