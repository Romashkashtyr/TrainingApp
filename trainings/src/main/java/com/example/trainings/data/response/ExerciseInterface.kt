package com.example.trainings.data.response

interface ExerciseInterface {
    val id: Int
    val primaryMuscles: List<PrimaryMuscles>
    val musclesName: String?
    val description: String?
}