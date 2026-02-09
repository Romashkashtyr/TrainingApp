package com.example.trainings.data.responseAPI

import com.example.trainings.data.response.WorkoutSession

interface WorkoutSessionAbs {
    var count: Int
    var next: String?
    var previous: String?
    var results: List<WorkoutSession>
}