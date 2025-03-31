package com.example.trainingapp.domain.di

import com.example.trainingapp.presentation.base.trainings.TrainingsListActivity
import com.example.trainingapp.presentation.base.trainings.TrainingsPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TrainingModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(activity: TrainingsListActivity)
    fun inject(presenter: TrainingsPresenter)

}