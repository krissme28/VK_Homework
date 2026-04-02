package com.example.vk_homework.appdetails.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDetailsDao {
    @Query("SELECT * FROM app_details WHERE id = :id")
    fun getAppDetailsFlow(id: String): Flow<AppDetailsEntity?>

    @Query("UPDATE app_details SET isInWishlist = :isInWishlist WHERE id = :id")
    suspend fun updateWishlistStatus(id: String, isInWishlist: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppDetails(entity: AppDetailsEntity)
}