package com.example.trainingapp.domain.di

import com.example.trainingapp.data.TrainingsRepositoryImpl
import com.example.trainingapp.domain.repository.TrainingsRepository
import dagger.Binds
import dagger.Module

@Module
interface Module {

    @Binds
    fun bindTrainingRepository(impl: TrainingsRepositoryImpl): TrainingsRepository
}