package com.example.trainingapp.data

import com.example.trainingapp.domain.Training
import com.example.trainingapp.domain.repository.TrainingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingsRepositoryImpl @Inject constructor() : TrainingsRepository {

    override suspend fun requestTrainingList(): List<Training> {
        TODO("Not yet implemented")
    }
}