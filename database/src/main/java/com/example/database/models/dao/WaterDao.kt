package com.example.database.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.models.main_modules.water.WaterDb
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {

    @Query("SELECT * FROM water WHERE date = :date")
    suspend fun getWaterByDate(date: String): WaterDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWater(entity: WaterDb)

    @Query("SELECT * FROM water ORDER BY date DESC")
    fun observeHistory(): Flow<List<WaterDb>>
}