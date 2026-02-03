package com.example.trainings.domain

import com.example.trainings.Training
import com.example.trainings.data.RequestResult
import com.example.trainings.data.response.TrainingVideos
import com.example.trainings.data.response.WorkoutSession
import kotlinx.coroutines.flow.Flow


interface TrainingsRepository {

    suspend fun requestTrainingList(): List<Training>

    fun getAllVideoFromServer(query: String): Flow<RequestResult<List<TrainingVideos>>>


    fun getAllWorkoutFromServer(query: String): Flow<RequestResult<List<WorkoutSession>>>
}