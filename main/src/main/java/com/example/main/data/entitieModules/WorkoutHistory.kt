package com.example.main.data.entitieModules

data class WorkoutHistory(
    val id: Long,
    val workoutId: String,
    val workoutName: String,
    val level: WorkoutLevel,
    val date: String
)