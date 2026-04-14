package com.example.vk_homework.applist.mappers

import com.example.vk_homework.applist.data.AppListItemDto
import com.example.vk_homework.applist.data.toDomain
import com.example.vk_homework.applist.data.toDomainList
import com.example.vk_homework.applist.domain.AppListCategory
import org.junit.Assert.assertEquals
import org.junit.Test

class AppListMapperTest {

    @Test
    fun `toDomain should correctly map DTO to Domain model`() {
        val dto = AppListItemDto(
            id = "1",
            name = "Test App",
            category = "Погода",
            iconUrl = "url",
            description = "Description"
        )

        val result = dto.toDomain()

        assertEquals("1", result.id)
        assertEquals(AppListCategory.WEATHER, result.appCategory)
    }

    @Test
    fun `mapToCategory should be case insensitive`() {
        val dto = createMockDto(category = "ПОГОДА")
        val result = dto.toDomain()

        assertEquals(AppListCategory.WEATHER, result.appCategory)
    }

    @Test
    fun `mapToCategory should map various strings to UTILITIES`() {
        val inputs = listOf("инструменты", "полезные инструменты", "utilities")

        inputs.forEach { input ->
            val dto = createMockDto(category = input)
            assertEquals(AppListCategory.UTILITIES, dto.toDomain().appCategory)
        }
    }

    @Test
    fun `mapToCategory should return APP category for unknown string`() {
        val dto = createMockDto(id = "1", category = "неизвестная категория")
        val result = dto.toDomain()

        assertEquals(AppListCategory.APP, result.appCategory)
    }

    @Test
    fun `toDomainList should map whole list correctly`() {
        val dtos = listOf(
            createMockDto(id = "1", category = "музыка"),
            createMockDto(id = "2", category = "финансы")
        )

        val result = dtos.toDomainList()

        assertEquals(2, result.size)
        assertEquals(AppListCategory.MUSIC, result[0].appCategory)
        assertEquals(AppListCategory.FINANCE, result[1].appCategory)
    }
}

private fun createMockDto(
    id: String = "1",
    category: String = "разное",
    name: String = "Test App"
) = AppListItemDto(
    id = id,
    name = name,
    category = category,
    iconUrl = "https://example.com",
    description = "Some description"
)