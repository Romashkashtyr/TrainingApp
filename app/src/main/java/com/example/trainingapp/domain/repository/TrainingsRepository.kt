package com.example.trainingapp.domain.repository

import com.example.trainingapp.domain.training.Training


interface TrainingsRepository {

    suspend fun requestTrainingList(): List<Training>
}