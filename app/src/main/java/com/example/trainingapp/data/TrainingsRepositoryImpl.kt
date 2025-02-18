package com.example.trainingapp.data

import com.example.trainingapp.domain.Training
import com.example.trainingapp.domain.di.AppScope
import com.example.trainingapp.domain.repository.TrainingsRepository
import javax.inject.Inject

@AppScope
class TrainingsRepositoryImpl @Inject constructor() : TrainingsRepository {

    override fun getTrainingList(): ArrayList<Training> {
        TODO("Not yet implemented")
    }
}