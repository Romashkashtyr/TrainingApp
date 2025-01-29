package com.example.trainingapp.domain

import kotlin.time.Duration

interface Training {
    val trainingName: String
    val duration: Duration
    val complexity: Int
}