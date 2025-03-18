package com.example.trainingapp.domain.di

import com.example.trainingapp.presentation.trainings.TrainingsListActivity
import com.example.trainingapp.presentation.trainings.TrainingsPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TrainingModule::class])
interface ApplicationComponent {

    fun inject(activity: TrainingsListActivity)
    fun inject(presenter: TrainingsPresenter)

}