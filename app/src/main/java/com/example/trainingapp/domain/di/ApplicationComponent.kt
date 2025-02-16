package com.example.trainingapp.domain.di

import com.example.trainingapp.presentation.trainings.TrainingsListActivity
import dagger.Component

@Component(modules = [Module::class])
interface ApplicationComponent {

    fun inject(activity: TrainingsListActivity)
}