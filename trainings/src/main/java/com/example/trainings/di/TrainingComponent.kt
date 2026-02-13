package com.example.trainings.di

import com.example.core.di.CoreComponent
import com.example.trainings.di.modules.NetworkModule
import com.example.trainings.di.modules.TrainingDatabaseModule
import com.example.trainings.di.modules.TrainingFitnessModule
import com.example.trainings.di.modules.TrainingsModule
import com.example.trainings.ui.TrainingsListActivity
import dagger.Component
import java.lang.IllegalStateException
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TrainingFitnessModule::class,
        TrainingsModule::class,
        NetworkModule::class,
        TrainingDatabaseModule::class
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

            return instance ?: throw IllegalStateException()
        }

        fun getTrainingInstance(): TrainingComponent {
            return if (instance != null) instance!! else throw IllegalStateException()
        }

    }


}