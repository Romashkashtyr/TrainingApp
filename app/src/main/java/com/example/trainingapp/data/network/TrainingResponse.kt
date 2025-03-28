package com.example.trainingapp.data.network

data class TrainingResponse(
    val workouts: List<WorkoutSession>,
    val videoResultTraining: List<VideoResultTraining>
)
