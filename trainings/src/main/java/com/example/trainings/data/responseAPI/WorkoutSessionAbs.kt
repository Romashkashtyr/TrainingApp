package com.example.trainings.data.responseAPI

import com.example.trainingapp.data.api.response.WorkoutSessionResult

interface WorkoutSessionAbs {
    var count: Int
    var next: String?
    var previous: String?
    var results: List<WorkoutSessionResult>
}