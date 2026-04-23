package com.example.trainings.data.response

interface ExerciseInterface {
    val id: String
    val primaryMuscles: List<PrimaryMuscles>
    val name: String?
    val description: String?
}