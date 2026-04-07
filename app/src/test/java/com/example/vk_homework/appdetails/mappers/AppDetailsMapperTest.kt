package com.example.vk_homework.appdetails.mappers

import com.example.vk_homework.appdetails.data.AppDetailsDto
import com.example.vk_homework.appdetails.data.AppDetailsMapper
import com.example.vk_homework.appdetails.data.toDomainList
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppDetailsMapperTest {

    private val mapper = AppDetailsMapper()

    @Test
    fun `mapToDomain should correctly map all fields when DTO is full`() {
        val dto = AppDetailsDto(
            rawName = "VK",
            developerName = "VK.com",
            id = "social_1",
            categoryTitle = "Социальные сети",
            ageLimit = 12,
            sizeInMb = 150.5f,
            iconPath = "https://icon.url",
            screenshots = listOf("s1.jpg", "s2.jpg"),
            fullDescription = "Best app"
        )

        val result = mapper.mapToDomain(dto)

        assertEquals("VK", result.name)
        assertEquals("VK.com", result.developer.name)
        assertEquals("social_1", result.appCategory.id)
        assertEquals("Социальные сети", result.appCategory.name)
        assertEquals(12, result.ageRating)
        assertEquals(150.5f, result.size)
        assertEquals("https://icon.url", result.iconUrl)
        assertEquals(2, result.screenshotUrlList.size)
        assertEquals("Best app", result.description)
        assertEquals(false, result.isInWishlist)
    }

    @Test
    fun `mapToDomain should use default values when DTO fields are null`() {
        val dto = AppDetailsDto(
            id = null,
            rawName = null,
            developerName = null,
            categoryTitle = null,
            ageLimit = null,
            sizeInMb = null,
            iconPath = null,
            screenshots = null,
            fullDescription = null
        )

        val result = mapper.mapToDomain(dto)

        assertEquals("", result.name)
        assertEquals("Неизвестен", result.developer.name)
        assertEquals("Разное", result.appCategory.name)
        assertEquals(0, result.ageRating)
        assertEquals(0f, result.size)
        assertTrue(result.screenshotUrlList.isEmpty())
    }

    @Test
    fun `toDomainList should map empty list to empty list`() {
        val dtos = emptyList<AppDetailsDto>()
        val result = dtos.toDomainList()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `toDomainList should map all items in the list`() {
        val dtos = listOf(
            AppDetailsDto(rawName = "App 1"),
            AppDetailsDto(rawName = "App 2")
        )

        val result = dtos.toDomainList()

        assertEquals(2, result.size)
        assertEquals("App 1", result[0].name)
        assertEquals("App 2", result[1].name)
    }

    @Test
    fun `mapToDomain should handle null screenshots by returning empty list`() {
        val dto = AppDetailsDto(screenshots = null)

        val result = mapper.mapToDomain(dto)

        assertTrue(result.screenshotUrlList.isEmpty())
    }
}
