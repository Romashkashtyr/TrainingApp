package com.example.trainings.data.response

data class Exercise(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    override val results: List<ExerciseInfo>
) : ExerciseInterface
