package com.example.trainings.data.responseAPI

import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession

interface TrainingResponseAbs {
    val workouts: List<WorkoutSession>
    val videoResultTraining: List<VideoResultTraining>
}