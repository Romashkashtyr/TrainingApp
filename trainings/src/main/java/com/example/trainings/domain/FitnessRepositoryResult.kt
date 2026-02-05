package com.example.trainings.domain

import com.example.trainings.data.response.TrainingVideos
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.VideosResponse
import com.example.trainings.data.response.WorkoutSession
import com.example.trainings.data.response.WorkoutSessionResult

interface FitnessRepositoryResult {

    suspend fun getWorkoutSessions(
        count: Int,
        next: String?,
        previous: String?,
        result: List<WorkoutSessionResult>
    ): Result<WorkoutSessionResponse>

    suspend fun getWorkoutVideos(
        count: Int,
        next: String,
        previous: String,
        result: List<VideoResultTraining>
    ): Result<VideosResponse>
}