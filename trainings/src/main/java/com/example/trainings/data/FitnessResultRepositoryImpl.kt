package com.example.trainings.data

import com.example.trainings.data.response.NetworkService
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.VideosResponse
import com.example.trainings.data.response.WorkoutSession
import com.example.trainings.data.response.WorkoutSessionResponse
import com.example.trainings.data.response.WorkoutSessionResult
import com.example.trainings.domain.FitnessRepositoryResult
import javax.inject.Inject

class FitnessResultRepositoryImpl @Inject constructor(
    private val api: NetworkService
) : FitnessRepositoryResult {


    override suspend fun getWorkoutSessions(
        count: Int,
        next: String?,
        previous: String?,
        result: List<WorkoutSessionResult>
    ): Result<WorkoutSessionResponse> {
        return try {
            val response = api.getWorkoutSessions(
                count = count,
                next = next,
            )
            if (response.isSuccessful) {
                val responseBody =
                    response.body() ?: return Result.failure(Exception("Error: ${response.code()}"))
                Result.success(responseBody)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getWorkoutVideos(
        count: Int,
        next: String,
        previous: String,
        result: List<VideoResultTraining>
    ): Result<VideosResponse> {
        return try {
            val response = api.getVideos(
                exercise = count,
                isMain = false
            )
            if (response.isSuccessful) {
                val responseBody =
                    response.body() ?: return Result.failure(Exception("Error: ${response.code()}"))
                Result.success(responseBody)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}