package com.example.trainingapp.domain.repository

import com.example.trainingapp.data.api.response.TrainingVideos
import com.example.trainingapp.data.api.response.VideoResultTraining
import com.example.trainingapp.data.api.response.WorkoutSession
import com.example.trainingapp.data.api.response.WorkoutSessionResult
import io.grpc.ClientStreamTracer.StreamInfo

interface FitnessRepositoryResult {

    suspend fun getWorkoutSessions(count: Int, next: String?, previous: String?, result: List<WorkoutSessionResult>): Result<WorkoutSession>

    suspend fun getWorkoutVideos(count: Int, next: String, previous: String, result: List<VideoResultTraining>): Result<List<TrainingVideos>>
}