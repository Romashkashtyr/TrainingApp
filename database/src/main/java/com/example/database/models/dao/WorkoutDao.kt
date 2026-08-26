package com.example.database.models.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.database.models.main_modules.WorkoutHistoryDb
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout_history ORDER BY date DESC")
    fun observeHistory(): Flow<List<WorkoutHistoryDb>>
}