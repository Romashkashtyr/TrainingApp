package com.example.trainings.di.modules

import com.example.trainings.ui.training_activity.TrainingsPresenter
import com.example.trainings.domain.usecases.GetCombineDataAndImageTrainings
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
class TrainingsModule {

    @Provides
    @Singleton
    fun provideTrainingPresenterFactory(useCase: GetCombineDataAndImageTrainings): TrainingsPresenter {
        return TrainingsPresenter(useCase)
    }

}

@Singleton
class TrainingFactory @Inject constructor(
    private val trainingPresenterProvider: Provider<TrainingsPresenter>
) {
    fun createTrainingPresenter(): TrainingsPresenter = trainingPresenterProvider.get()
}


