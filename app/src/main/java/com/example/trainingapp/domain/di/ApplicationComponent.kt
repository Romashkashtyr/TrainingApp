package com.example.trainingapp.domain.di

import android.content.Context
import com.example.trainingapp.data.repository.AuthRepositoryImpl
import com.example.trainingapp.domain.di.modules.AppModule
import com.example.trainingapp.domain.di.modules.ExceptionModule
import com.example.trainingapp.domain.di.modules.FitnessResultModule
import com.example.trainingapp.domain.di.modules.NetworkModule
import com.example.trainingapp.domain.di.modules.TrainingModule
import com.example.trainingapp.domain.repository.FitnessResultRepositoryImpl
import com.example.trainingapp.presentation.signin.AuthorizationActivity
import com.example.trainingapp.presentation.trainings.TrainingsListActivity
import com.example.trainingapp.presentation.trainings.TrainingsPresenter
import com.google.firebase.auth.FirebaseAuth
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
    AppModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: TrainingsListActivity)
    fun inject(activity: AuthorizationActivity)


    @Component.Builder
    interface Builder {
        @BindsInstance fun context(context: Context): Builder
        fun build(): ApplicationComponent
    }





}