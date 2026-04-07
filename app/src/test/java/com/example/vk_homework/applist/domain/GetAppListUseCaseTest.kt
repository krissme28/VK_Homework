package com.example.vk_homework.applist.domain

import com.example.vk_homework.applist.domain.usecases.GetAppListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetAppListUseCaseTest {

    private val repository = mockk<AppRepository>()
    private val useCase = GetAppListUseCase(repository)

    @Test
    fun `invoke should return list of apps from repository`() = runTest {
        val mockList = listOf(
            AppListItem(id = "1", name = "App 1", appCategory = AppListCategory.GAME, iconUrl = "", description = ""),
            AppListItem(id = "2", name = "App 2", appCategory = AppListCategory.MUSIC, iconUrl = "", description = "")
        )
        coEvery { repository.getAppList() } returns mockList

        val result = useCase()

        assertEquals(2, result.size)
        assertEquals("App 1", result[0].name)
    }

    @Test
    fun `invoke should call repository exactly once`() = runTest {
        coEvery { repository.getAppList() } returns emptyList()

        useCase()

        coVerify(exactly = 1) { repository.getAppList() }
    }

    @Test
    fun `invoke should return empty list when repository is empty`() = runTest {
        coEvery { repository.getAppList() } returns emptyList()

        val result = useCase()

        assertTrue(result.isEmpty())
    }

    @Test(expected = Exception::class)
    fun `invoke should throw exception when repository fails`() = runTest {
        coEvery { repository.getAppList() } throws Exception("Data error")

        useCase()
    }

    @Test
    fun `invoke should return correct data even with one item`() = runTest {
        val mockList = listOf(AppListItem(id = "1", name = "Single App", appCategory = AppListCategory.BOOKS, iconUrl = "", description = ""))
        coEvery { repository.getAppList() } returns mockList

        val result = useCase()

        assertEquals(1, result.size)
        assertEquals(AppListCategory.BOOKS, result[0].appCategory)
    }
}