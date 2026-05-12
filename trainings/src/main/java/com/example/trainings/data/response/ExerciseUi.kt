package com.example.trainings.data.response

data class ExerciseUi(
    val id: String,
    val name: String?,
    val description: String?,
    val primaryMuscles: List<PrimaryMuscles>,
    val imageUrl: String,
)