package com.example.trainingapp.domain.di.modules

import com.example.trainingapp.data.repository.TrainingsRepositoryImpl
import com.example.trainingapp.domain.repository.TrainingsRepository
import dagger.Binds
import dagger.Module

@Module
interface TrainingModule {


    @Binds
    fun bindTrainingRepository(impl: TrainingsRepositoryImpl): TrainingsRepository





//    @AppScope
//    @Binds
//    fun bindTrainingRepository(impl: TrainingsRepositoryImpl): TrainingsRepository
}