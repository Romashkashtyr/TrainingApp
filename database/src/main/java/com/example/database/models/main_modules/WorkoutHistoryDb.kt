package com.example.database.models.main_modules

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_history")
data class WorkoutHistoryDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workoutId: Long,
    val workoutName: String,
    val level: String,
    val date: String
)
