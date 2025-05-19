package com.example.trainingapp.data.api.response

import com.example.trainingapp.data.api.response.responseAPI.TrainingResponseAbs

data class TrainingResponse(
    override val workouts: List<WorkoutSession>,
    override val videoResultTraining: List<VideoResultTraining>
) : TrainingResponseAbs
