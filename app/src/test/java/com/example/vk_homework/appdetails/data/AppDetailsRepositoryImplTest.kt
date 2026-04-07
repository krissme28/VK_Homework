package com.example.vk_homework.appdetails.data

import com.example.vk_homework.applist.data.CatalogApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AppDetailsRepositoryImplTest {

    @Before
    fun setup() {
        mockkStatic(android.util.Log::class)
        every { android.util.Log.d(any(), any()) } returns 0
        every { android.util.Log.e(any(), any()) } returns 0
    }

    private val catalogApi = mockk<CatalogApi>()
    private val dao = mockk<AppDetailsDao>(relaxed = true)
    private val entityMapper = AppDetailsEntityMapper()
    private val detailsMapper = AppDetailsMapper()

    private val repository = AppDetailsRepositoryImpl(
        catalogApi = catalogApi,
        dao = dao,
        entityMapper = entityMapper,
        detailsMapper = detailsMapper
    )

    @Test
    fun `getAppDetails should fetch from API when DB is empty`() = runTest {
        val appId = "1"
        every { dao.getAppDetailsFlow(appId) } returns flowOf(null)
        val mockDto = AppDetailsDto(id = appId, rawName = "Test App")
        coEvery { catalogApi.getAppDetails(appId) } returns mockDto

        val result = repository.getAppDetails(appId)

        assertEquals("Test App", result.name)
        coVerify(exactly = 1) { dao.insertAppDetails(any()) }
    }

    @Test
    fun `toggleWishlist should change status in DB`() = runTest {
        val appId = "1"
        val mockEntity = createTestEntity(appId, false)
        every { dao.getAppDetailsFlow(appId) } returns flowOf(mockEntity)

        repository.toggleWishlist(appId)

        coVerify { dao.updateWishlistStatus(appId, true) }
    }

    @Test
    fun `observeAppDetails should return mapped domain data`() = runTest {
        val appId = "1"
        val mockEntity = createMockEntity(id = appId, name = "DB App")
        every { dao.getAppDetailsFlow(appId) } returns flowOf(mockEntity)

        repository.observeAppDetails(appId).collect { result ->
            assertEquals("DB App", result.name)
        }
    }

    @Test
    fun `getAppDetails should return data from DB if exists`() = runTest {
        val appId = "1"
        val mockEntity = createMockEntity(id = appId, name = "Cached App")
        every { dao.getAppDetailsFlow(appId) } returns flowOf(mockEntity)

        val result = repository.getAppDetails(appId)

        assertEquals("Cached App", result.name)
        coVerify(exactly = 0) { catalogApi.getAppDetails(any()) }
    }

    @Test
    fun `toggleWishlist should do nothing if app not found`() = runTest {
        val appId = "non_existent"
        every { dao.getAppDetailsFlow(appId) } returns flowOf(null)

        repository.toggleWishlist(appId)

        coVerify(exactly = 0) { dao.updateWishlistStatus(any(), any()) }
    }
}

private fun createTestEntity(
    id: String = "1",
    isInWishlist: Boolean = false
) = AppDetailsEntity(
    id = id,
    name = "Test",
    developerName = "Dev",
    categoryTitle = "Cat",
    fullDescription = "Desc",
    ageLimit = 0,
    sizeInMb = 0f,
    iconPath = "",
    screenshots = emptyList(),
    isInWishlist = isInWishlist
)

private fun createMockEntity(id: String, name: String = "Test App", isInWishlist: Boolean = false) = AppDetailsEntity(
    id = id,
    name = name,
    developerName = "Dev",
    categoryTitle = "Category",
    ageLimit = 0,
    sizeInMb = 0f,
    iconPath = "",
    screenshots = emptyList(),
    fullDescription = "Description",
    isInWishlist = isInWishlist
)