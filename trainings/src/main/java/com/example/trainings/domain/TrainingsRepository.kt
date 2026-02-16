package com.example.trainings.domain

import com.example.trainings.Training
import com.example.trainings.data.RequestResult
import com.example.trainings.data.response.TrainingData
import com.example.trainings.data.response.TrainingResponse
import com.example.trainings.data.response.TrainingVideos
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface TrainingsRepository {

//    suspend fun requestTrainingList(): List<Training>
//
    fun getAllVideoFromServer(query: String): Flow<RequestResult<List<VideoResultTraining>>>

    fun getAll(query: String): Flow<RequestResult<List<TrainingData>>>
//
//
//    fun getAllWorkoutFromServer(query: String): Flow<RequestResult<List<WorkoutSession>>>

    //fun observeTrainingResponse(): Flow<TrainingResponse>
    fun observeTrainingResponse(): Flow<TrainingData>

    suspend fun refresh()

    suspend fun <T> safeApiCall(call: suspend () -> Response<T>) :Result<T>
}