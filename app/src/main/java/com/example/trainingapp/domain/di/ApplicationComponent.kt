package com.example.trainingapp.domain.di

import com.example.trainingapp.presentation.base.trainings.TrainingsListActivity
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(modules = [TrainingModule::class])
interface ApplicationComponent {

    fun inject(activity: TrainingsListActivity)


    interface ApplicationComponentBuilder {

    }
}