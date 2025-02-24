package com.example.trainingapp.domain.repository

import com.example.trainingapp.data.ConcreteTraining
import com.example.trainingapp.domain.Training


interface TrainingsRepository {

    suspend fun requestTrainingList(): List<ConcreteTraining>
}