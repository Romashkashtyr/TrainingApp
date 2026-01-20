package com.example.main.di

import com.example.core.exception.FirebaseExceptionCatcher
import com.example.core.repository.MainRepository
import com.example.main.data.MainRepositoryImpl
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import javax.inject.Singleton

@Module
interface MainModule {

    // TODO()

    @Provides
    @Singleton
    fun provideMainRepository(
        catcher: FirebaseExceptionCatcher,
    ): MainRepository {
        return MainRepositoryImpl(catcher)
    }

    @Binds
    @Singleton
    fun bindsMainRepository(impl: MainRepositoryImpl): MainRepository
}