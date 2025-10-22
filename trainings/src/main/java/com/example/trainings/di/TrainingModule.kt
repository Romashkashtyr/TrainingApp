package com.example.trainings.di

import com.example.trainings.data.TrainingsRepositoryImpl
import com.example.trainings.domain.TrainingsRepository
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