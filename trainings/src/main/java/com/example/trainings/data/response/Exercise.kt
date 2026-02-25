package com.example.trainings.data.response

data class Exercise(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ExerciseInfo>
)
