package com.example.trainingapp.data

import com.example.trainingapp.domain.Training
import kotlin.time.Duration

data class ConcreteTraining(
    override val trainingName: String,
    override val duration: Duration,
    override val complexity: Int
): Training {
}

