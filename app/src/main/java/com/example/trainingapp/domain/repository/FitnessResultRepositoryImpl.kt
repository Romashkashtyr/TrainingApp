package com.example.trainingapp.domain.repository

import com.example.trainingapp.data.api.NetworkService
import com.example.trainingapp.data.api.response.TrainingVideos
import com.example.trainingapp.data.api.response.VideoResultTraining
import com.example.trainingapp.data.api.response.WorkoutSession
import com.example.trainingapp.data.api.response.WorkoutSessionResult
import javax.inject.Inject

class FitnessResultRepositoryImpl @Inject constructor(private val api: NetworkService) : FitnessRepositoryResult {

    override suspend fun getWorkoutSessions(
        count: Int = 10,
        nextPageToken: String,
        previous: String
    ): Result<WorkoutSession> {
        return try {
            val response = api.getWorkoutSessions(
                count = count,
                next = nextPageToken,
                previous = previous
            )
            if (response.isSuccessful){
                val responseBody = response.body() ?: return Result.failure(Exception("Error: ${response.code()}"))
                Result.success(responseBody)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getWorkoutVideos(
        exercisedId: Int? = null,
        isMain: Boolean? = null
    ): Result<List<TrainingVideos>> {
        return try {
            val response = api.getVideos(
                exercise = exercisedId,
                isMain = isMain
            )
            if (response.isSuccessful) {
                val responseBody = response.body() ?: return Result.failure(Exception("Error: ${response.code()}"))
                Result.success(responseBody)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getWorkoutSessions(
        count: Int = 10,
        next: String?,
        previous: String?,
        result: List<WorkoutSessionResult>
    ): Result<WorkoutSession> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkoutVideos(
        count: Int,
        next: String,
        previous: String,
        result: List<VideoResultTraining>
    ): Result<List<TrainingVideos>> {
        TODO("Not yet implemented")
    }


}