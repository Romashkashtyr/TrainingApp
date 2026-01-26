package com.example.trainings.di.modules

import com.example.trainings.data.FitnessResultRepositoryImpl
import com.example.trainings.domain.TrainingsRepository
import com.example.trainings.ui.TrainingsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
class TrainingsModule {

    @Provides
    @Singleton
    fun provideTrainingPresenterFactory(trainingsRepository: TrainingsRepository, repositoryImpl: FitnessResultRepositoryImpl): TrainingsPresenter {
        return TrainingsPresenter(trainingsRepository, repositoryImpl)
    }

}


@Singleton
class TrainingFactory @Inject constructor(
    private val trainingPresenterProvider: Provider<TrainingsPresenter>
) {
    fun createTrainingPresenter(): TrainingsPresenter = trainingPresenterProvider.get()
}


