package com.example.trainings.di

import com.google.android.datatransport.runtime.dagger.Component

@Component(modules = [FitnessResultModule::class, TrainingModule::class, TrainingsModule::class])
abstract class TrainingComponent {

    companion object {
        private var trainingComponent: TrainingComponent? = null

        fun init(): TrainingComponent {
            if (trainingComponent == null) {
                trainingComponent = DaggerTrainingComponent.create()
            }

            return trainingComponent!!
        }

    }


}