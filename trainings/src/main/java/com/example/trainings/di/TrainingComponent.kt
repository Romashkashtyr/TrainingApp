package com.example.trainings.di

import com.example.core.di.CoreComponent
import com.example.trainings.di.modules.TrainingFitnessModule
import com.example.trainings.di.modules.TrainingsModule
import com.example.trainings.ui.TrainingsListActivity
import com.google.android.datatransport.runtime.dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TrainingFitnessModule::class,
        TrainingsModule::class
    ],
    dependencies = [
        CoreComponent::class
    ]
)
interface TrainingComponent {


    fun inject(activity: TrainingsListActivity)

    @Component.Builder
    interface Builder {

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): TrainingComponent
    }


    companion object {

        private var instance: TrainingComponent? = null

        fun init(coreComponent: CoreComponent): TrainingComponent {
            if (instance == null) {
                instance = DaggerTrainingComponent.builder().coreComponent(coreComponent).build()
            }

            return instance!!
        }

    }


}