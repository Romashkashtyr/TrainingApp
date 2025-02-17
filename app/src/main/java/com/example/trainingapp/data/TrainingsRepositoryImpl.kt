package com.example.trainingapp.data

import com.example.trainingapp.domain.di.AppScope
import com.example.trainingapp.domain.repository.TrainingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@AppScope
class TrainingsRepositoryImpl @Inject constructor() : TrainingsRepository {
    override fun getTrainingList() {
        TODO("Not yet implemented")
    }
}