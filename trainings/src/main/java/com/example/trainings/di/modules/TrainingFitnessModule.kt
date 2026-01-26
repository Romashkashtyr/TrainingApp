package com.example.trainings.di.modules

import com.example.trainings.data.FitnessResultRepositoryImpl
import com.example.trainings.data.TrainingsRepositoryImpl
import com.example.trainings.domain.FitnessRepositoryResult
import com.example.trainings.domain.TrainingsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface TrainingFitnessModule {

    @Singleton
    @Binds
    fun bindFitnessRepositoryResult(impl: FitnessResultRepositoryImpl): FitnessRepositoryResult


    @Singleton
    @Binds
    fun bindTrainingRepository(impl: TrainingsRepositoryImpl): TrainingsRepository
}