package com.example.trainings.data.response

data class TrainingResponse(
    override val workouts: List<WorkoutSession>,
    override val videoResultTraining: List<VideoResultTraining>
) : TrainingResponseAbs
