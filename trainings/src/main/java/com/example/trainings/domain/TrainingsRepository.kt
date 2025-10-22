package com.example.trainings.domain

import com.example.trainings.Training


interface TrainingsRepository {

    suspend fun requestTrainingList(): List<Training>
}