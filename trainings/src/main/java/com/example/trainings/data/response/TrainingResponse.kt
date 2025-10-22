package com.example.trainings.data.response

import com.example.trainings.data.responseAPI.TrainingResponseAbs

data class TrainingResponse(
    override val workouts: List<WorkoutSession>,
    override val videoResultTraining: List<VideoResultTraining>
) : TrainingResponseAbs
