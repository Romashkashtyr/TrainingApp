package com.example.trainingapp.domain.di.modules

import com.example.core.exception.ExceptionCatcher
import dagger.Module
import dagger.Provides


@Module
class ExceptionModule {

    @Provides
    fun provideExceptionCatcher(): ExceptionCatcher {
        return ExceptionCatcher()
    }
}