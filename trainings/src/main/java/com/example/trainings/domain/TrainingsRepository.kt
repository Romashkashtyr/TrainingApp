package com.example.trainings.domain

import com.example.trainings.data.RequestResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface TrainingsRepository {

    suspend fun loadExercises(): Exercise

    suspend fun getCachedExercises(): Exercise?
}