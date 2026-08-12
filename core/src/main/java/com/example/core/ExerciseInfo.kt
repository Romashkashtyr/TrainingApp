package com.example.core

data class ExerciseInfo (
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val muscleNames: List<String>
)
