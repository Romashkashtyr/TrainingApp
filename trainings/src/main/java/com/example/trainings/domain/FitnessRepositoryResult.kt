package com.example.trainings.domain

import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.VideosResponse
import com.example.trainings.data.response.WorkoutSession

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