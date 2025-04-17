package com.example.trainingapp.data.network

import com.google.gson.annotations.SerializedName

interface WorkoutSessionAbs {
    var count: Int
    var next: String?
    var previous: String?
    var results: List<WorkoutSessionResult>
}