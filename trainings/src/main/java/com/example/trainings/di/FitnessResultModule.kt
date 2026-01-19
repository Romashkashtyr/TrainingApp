package com.example.trainings.di

import com.example.trainings.data.FitnessResultRepositoryImpl
import com.example.trainings.domain.FitnessRepositoryResult
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface FitnessResultModule {

    @Singleton
    @Binds
    fun bindFitnessRepositoryResult(impl: FitnessResultRepositoryImpl): FitnessRepositoryResult
}