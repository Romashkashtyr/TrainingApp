package com.example.trainingapp.domain.di.modules

import com.example.trainingapp.data.repository.AuthRepositoryImpl
import com.example.trainingapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module


@Module
interface AuthModule {

    @Binds
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}