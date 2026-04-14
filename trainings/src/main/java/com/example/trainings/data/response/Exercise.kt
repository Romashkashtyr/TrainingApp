package com.example.trainings.data.response

data class Exercise(
    override val id: Int,
    override val primaryMuscles: List<PrimaryMuscles>,
    override val musclesName: String?,
    override val description: String?,
) : ExerciseInterface