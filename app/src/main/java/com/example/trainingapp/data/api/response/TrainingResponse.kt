package com.example.trainingapp.data.api.response

import com.example.trainingapp.data.api.response.responseAbstract.TrainingResponseAbs

data class TrainingResponse(
    override val workouts: List<WorkoutSession>,
    override val videoResultTraining: List<VideoResultTraining>
) : TrainingResponseAbs
