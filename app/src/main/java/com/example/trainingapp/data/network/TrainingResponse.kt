package com.example.trainingapp.data.network

data class TrainingResponse(
    override val workouts: List<WorkoutSession>,
    override val videoResultTraining: List<VideoResultTraining>
) : TrainingResponseAbs
