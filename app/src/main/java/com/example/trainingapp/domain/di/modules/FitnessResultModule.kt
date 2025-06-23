package com.example.trainingapp.domain.di.modules

import com.example.trainingapp.domain.repository.FitnessRepositoryResult
import com.example.trainingapp.domain.repository.FitnessResultRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface FitnessResultModule {

    @Binds
    fun bindFitnessRepositoryResult(impl: FitnessResultRepositoryImpl): FitnessRepositoryResult
}