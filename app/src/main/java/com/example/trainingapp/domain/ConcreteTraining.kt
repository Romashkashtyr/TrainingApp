package com.example.trainingapp.domain

import kotlin.time.Duration

data class ConcreteTraining(
    override val trainingName: String,
    override val duration: Duration,
    override val complexity: Int
): Training {
}

interface Training {
    val trainingName: String
    val duration: Duration
    val complexity: Int
}