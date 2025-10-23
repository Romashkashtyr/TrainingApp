package com.example.auth.domain.di

import com.example.auth.repository.AuthRepository
import com.example.auth.data.AuthRepositoryImpl
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module


@Module
interface AuthBindModule {

    @Binds
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}