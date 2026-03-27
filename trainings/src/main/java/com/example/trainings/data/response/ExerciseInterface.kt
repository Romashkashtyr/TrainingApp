package com.example.trainings.data.response

interface ExerciseInterface {
    val count: Int
    val next: String?
    val previous: String?
    val results: List<ExerciseInfo>
}