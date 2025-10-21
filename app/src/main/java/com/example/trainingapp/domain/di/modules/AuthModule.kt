package com.example.trainingapp.domain.di.modules

import com.example.trainingapp.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
interface AuthModule {

    @Binds
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}