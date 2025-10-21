package com.example.trainingapp.domain.di

import android.content.Context
import com.example.trainingapp.domain.di.modules.AppModule
import com.example.trainingapp.domain.di.modules.AuthModule
import com.example.trainingapp.domain.di.modules.ExceptionModule
import com.example.trainingapp.domain.di.modules.FitnessResultModule
import com.example.trainingapp.domain.di.modules.NetworkModule
import com.example.trainingapp.domain.di.modules.TrainingModule
import com.example.auth.AuthorizationActivity
import com.example.splash.ui.SplashActivity
import com.example.trainings.TrainingsListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
    TrainingModule::class,
    NetworkModule::class,
    FitnessResultModule::class,
    ExceptionModule::class,
    AppModule::class,
    AuthModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: com.example.trainings.TrainingsListActivity)
    fun inject(activity: com.example.auth.AuthorizationActivity)
    fun inject(activity: SplashActivity)


    @Component.Builder
    interface Builder {
        @BindsInstance fun context(context: Context): Builder
        fun build(): ApplicationComponent
    }





}