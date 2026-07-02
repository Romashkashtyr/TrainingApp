package com.example.trainings.domain

import com.example.database.models.training_modules.FavoriteExerciseDbo
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.FullExercise
import kotlinx.coroutines.flow.Flow


interface TrainingsRepository {

    suspend fun loadExercises(): List<Exercise>

    suspend fun getCachedExercises(): List<Exercise>?

    fun getExerciseImageUrl(id: String): String

    suspend fun addFavorite(exercise: FullExercise)

    suspend fun removeFavorite(id: String)

    suspend fun isFavorite(id: String): Boolean

    suspend fun getFavoriteIds(): List<String>

    suspend fun getExerciseById(id: String): FullExercise

//    suspend fun getFavoriteExercises(): List<FullExercise>

    fun observeFavoriteExercises(): Flow<List<FullExercise>>

}