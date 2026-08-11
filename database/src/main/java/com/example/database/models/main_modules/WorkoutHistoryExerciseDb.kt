package com.example.database.models.main_modules

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_history_exercise")
data class WorkoutHistoryExerciseDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workoutHistoryId: Long,
    val exerciseId: Long,
    val exerciseName: String
)
