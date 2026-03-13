package com.example.trainings.domain

interface FitnessRepositoryResult {

    suspend fun getWorkoutSessions(
        count: Int,
        next: String?,
        previous: String?,
        result: List<WorkoutSession>
    ): Result<WorkoutSession>

    suspend fun getWorkoutVideos(
        count: Int,
        next: String,
        previous: String,
        result: List<VideoResultTraining>
    ): Result<VideosResponse>
}