package com.example.trainingapp.domain.di.modules

import com.example.splash.di.SplashComponent
import dagger.Module

@Module(
    subcomponents = [
        SplashComponent::class,
    ]
)
object SubcomponentsModule