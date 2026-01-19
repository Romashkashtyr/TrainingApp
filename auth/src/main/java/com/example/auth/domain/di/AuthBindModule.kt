package com.example.auth.domain.di


import com.example.auth.data.AuthRepositoryImpl
import com.example.core.repository.AuthRepository
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import javax.inject.Singleton


@Module
interface AuthBindModule {

    @Binds
    @Singleton
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}