package com.example.core.di.modules

import com.example.core.navigation.Router
import com.example.core.navigation.RouterHolder
import dagger.Module
import dagger.Provides

@Module
object RouterModule {

    @Provides
    fun provideRouter(): Router {
        return RouterHolder.router
    }
}