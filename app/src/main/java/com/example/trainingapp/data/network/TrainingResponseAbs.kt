package com.example.trainingapp.data.network

interface TrainingResponseAbs {
    val workouts: List<WorkoutSession>
    val videoResultTraining: List<VideoResultTraining>
}