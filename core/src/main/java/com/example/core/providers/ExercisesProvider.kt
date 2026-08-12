package com.example.core.providers

import com.example.core.ExerciseInfo

interface ExercisesProvider {

    suspend fun getExercises(): List<ExerciseInfo>
}