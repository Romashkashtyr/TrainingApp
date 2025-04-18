package com.example.trainingapp.data.network

import com.example.trainingapp.data.api.NetworkService

class FitnessResultRepository(private val api: NetworkService) {

    suspend fun getWorkoutSessions(
        count: Int = 10,
        nextPageToken: String
    ): Result<WorkoutSession> {
        return try {
            val response = api.getWorkoutSessions(
                count = count,
                next = nextPageToken
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
}