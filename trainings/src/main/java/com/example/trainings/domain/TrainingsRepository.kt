package com.example.trainings.domain

import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.FullExercise


interface TrainingsRepository {

    suspend fun loadExercises(): List<Exercise>

    suspend fun getCachedExercises(): List<Exercise>?

    fun getExerciseImageUrl(id: String): String

    suspend fun addFavorite(exercise: FullExercise)

    suspend fun removeFavorite(id: String)

    suspend fun isFavorite(id: String): Boolean

}