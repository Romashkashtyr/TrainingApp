package com.example.main.data.entitieModules

data class WorkoutExercise (
    val exerciseId: Int,
    val exerciseName: String,
    val description: String?,
    val imageUrl: String,
    val durationSeconds: Int
)