package com.example.trainings.data.response

data class Exercise(
    override val id: String,
    override val primaryMuscles: List<PrimaryMuscles>,
    override val name: String?,
    override val description: String?,
) : ExerciseInterface