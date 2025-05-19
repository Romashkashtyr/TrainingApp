package com.example.trainingapp.domain.di

import com.example.trainingapp.domain.repository.FitnessResultRepositoryImpl
import com.example.trainingapp.presentation.trainings.TrainingsListActivity
import com.example.trainingapp.presentation.trainings.TrainingsPresenter
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [TrainingModule::class, NetworkModule::class, FitnessResultModule::class])
interface ApplicationComponent {

    fun inject(activity: TrainingsListActivity)
    fun inject(presenter: TrainingsPresenter)
    fun inject(repositoryFitnessResult: FitnessResultRepositoryImpl)

    @Component.Factory
    interface AppComponentFactory {

        fun create(@BindsInstance retrofit: Retrofit): ApplicationComponent
    }

}