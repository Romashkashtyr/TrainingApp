package com.example.trainings.di.modules

import com.example.trainings.data.repository.TrainingsRepositoryImpl
import com.example.trainings.domain.TrainingsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface TrainingFitnessModule {

    @Singleton
    @Binds
    fun bindTrainingRepository(impl: TrainingsRepositoryImpl): TrainingsRepository


}