package com.example.trainings.domain

import com.example.trainings.data.response.Exercise


interface TrainingsRepository {

    suspend fun loadExercises(): List<Exercise>

    suspend fun getCachedExercises(): List<Exercise>?

    fun getExerciseImageUrl(id: String): String

}