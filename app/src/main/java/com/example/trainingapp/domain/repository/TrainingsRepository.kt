package com.example.trainingapp.domain.repository

import com.example.trainingapp.domain.Training


interface TrainingsRepository {

    suspend fun getTrainingList(): ArrayList<Training>
}