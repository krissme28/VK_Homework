package com.example.vk_homework.applist.data

import com.example.vk_homework.applist.domain.AppListCategory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppRepositoryImplTest {

    private val catalogApi = mockk<CatalogApi>()

    private val repository = AppRepositoryImpl(catalogApi)

    private fun createMockDto(id: String, name: String, category: String) = AppListItemDto(
        id = id,
        name = name,
        category = category,
        iconUrl = "url",
        description = "desc"
    )

    @Test
    fun `getAppList should return mapped domain list on success`() = runTest {
        val mockDtos = listOf(createMockDto("1", "App 1", "музыка"))

        coEvery { catalogApi.getCatalog() } returns mockDtos

        val result = repository.getAppList()

        assertEquals(1, result.size)
        coVerify(exactly = 1) { catalogApi.getCatalog() }
    }

    @Test
    fun `getAppList should call catalogApi exactly once`() = runTest {
        coEvery { catalogApi.getCatalog() } returns emptyList()

        repository.getAppList()

        coVerify(exactly = 1) { catalogApi.getCatalog() }
    }

    @Test
    fun `getAppList should return empty list when API returns empty`() = runTest {
        coEvery { catalogApi.getCatalog() } returns emptyList()

        val result = repository.getAppList()

        assertTrue(result.isEmpty())
    }

    @Test(expected = Exception::class)
    fun `getAppList should throw exception when API fails`() = runTest {
        coEvery { catalogApi.getCatalog() } throws Exception("404 Not Found")

        repository.getAppList()
    }

    @Test
    fun `getAppList should correctly map category names through mapper`() = runTest {
        val mockDtos = listOf(createMockDto("1", "Weather App", "погода"))
        coEvery { catalogApi.getCatalog() } returns mockDtos

        val result = repository.getAppList()

        assertEquals(AppListCategory.WEATHER, result[0].appCategory)
    }
}
