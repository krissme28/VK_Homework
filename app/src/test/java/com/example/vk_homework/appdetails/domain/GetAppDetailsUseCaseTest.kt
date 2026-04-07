package com.example.vk_homework.appdetails.domain

import com.example.vk_homework.appdetails.domain.usecases.GetAppDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAppDetailsUseCaseTest {
    private val repository = mockk<AppDetailsRepository>()
    private val useCase = GetAppDetailsUseCase(repository)

    @Test
    fun `execute should call repository with correct id`() = runTest {
        val appId = "test_id"
        val expectedDetails = mockk<AppDetails>() // просто заглушка объекта
        coEvery { repository.getAppDetails(appId) } returns expectedDetails

        useCase(appId)

        coVerify(exactly = 1) { repository.getAppDetails(appId) }
    }

    @Test
    fun `execute should return data from repository`() = runTest {
        val appId = "123"
        val expectedDetails = mockk<AppDetails>()
        coEvery { repository.getAppDetails(appId) } returns expectedDetails

        val result = useCase(appId)

        assertEquals(expectedDetails, result)
    }

    @Test(expected = Exception::class)
    fun `execute should throw exception when repository fails`() = runTest {
        coEvery { repository.getAppDetails(any()) } throws Exception("Network error")

        useCase("any_id")
    }

    @Test
    fun `execute should return details even if description is empty`() = runTest {
        val mockData = mockk<AppDetails>()
        coEvery { mockData.description } returns ""
        coEvery { repository.getAppDetails(any()) } returns mockData

        val result = useCase("id")

        assertEquals("", result.description)
    }

    @Test
    fun `execute with empty id should still call repository`() = runTest {
        coEvery { repository.getAppDetails("") } returns mockk()

        useCase("")

        coVerify { repository.getAppDetails("") }
    }
}