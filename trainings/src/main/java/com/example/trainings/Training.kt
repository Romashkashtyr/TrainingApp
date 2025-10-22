package com.example.trainings

import kotlin.time.Duration

interface Training {
    val trainingName: String
    val duration: Duration
    val complexity: Int
}