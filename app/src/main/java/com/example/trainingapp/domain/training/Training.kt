package com.example.trainingapp.domain.training

import kotlin.time.Duration

interface Training {
    val trainingName: String
    val duration: Duration
    val complexity: Int
}