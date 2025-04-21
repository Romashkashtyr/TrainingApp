package com.example.trainingapp.data.api

import com.example.trainingapp.data.api.response.TrainingVideos
import com.example.trainingapp.data.api.response.VideoResultTraining
import com.example.trainingapp.data.api.response.WorkoutSession

class FitnessResultRepository(private val api: NetworkService) {

    suspend fun getWorkoutSessions(
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
                Result.success(response.body()!!)  // неправильно
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getWorkoutVideos(
        exercisedId: Int? = null,
        isMain: Boolean? = null
    ): Result<List<VideoResultTraining>> {
        return try {
            val response = api.getVideos(
                exercise = exercisedId,
                isMain = isMain
            )
            if (response.isSuccessful) {
                Result.success(response.body()?.result!!)  // неправильно
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}