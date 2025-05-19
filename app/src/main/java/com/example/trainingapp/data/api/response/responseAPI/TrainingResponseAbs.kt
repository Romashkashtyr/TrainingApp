package com.example.trainingapp.data.api.response.responseAPI

import com.example.trainingapp.data.api.response.VideoResultTraining
import com.example.trainingapp.data.api.response.WorkoutSession

interface TrainingResponseAbs {
    val workouts: List<WorkoutSession>
    val videoResultTraining: List<VideoResultTraining>
}