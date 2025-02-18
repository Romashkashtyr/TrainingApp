package com.example.trainingapp.domain.repository

import com.example.trainingapp.domain.Training


interface TrainingsRepository {

    fun getTrainingList(): ArrayList<Training>
}