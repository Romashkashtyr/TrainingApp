package com.example.trainings.di.modules

import com.example.trainings.ui.training_activity.TrainingsPresenter
import com.example.trainings.domain.usecases.GetCombineDataAndImageTrainings
import com.example.trainings.domain.usecases.GetExerciseByIdUseCase
import com.example.trainings.domain.usecases.ToggleFavoriteUseCase
import com.example.trainings.ui.fragment_detail_training.FragmentDetailPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
class TrainingsModule {

    @Provides
    @Singleton
    fun provideTrainingPresenterFactory(
        useCase: GetCombineDataAndImageTrainings,
        toggleFavoriteUseCase: ToggleFavoriteUseCase
    ): TrainingsPresenter {
        return TrainingsPresenter(useCase, toggleFavoriteUseCase)
    }

    @Provides
    @Singleton
    fun provideFragmentDetailPresenterFactory(
        useCase: GetExerciseByIdUseCase,
        toggleFavoriteUseCase: ToggleFavoriteUseCase): FragmentDetailPresenter {
        return FragmentDetailPresenter(useCase,toggleFavoriteUseCase)
    }



}

@Singleton
class TrainingFactory @Inject constructor(
    private val trainingPresenterProvider: Provider<TrainingsPresenter>
) {
    fun createTrainingPresenter(): TrainingsPresenter = trainingPresenterProvider.get()
}


@Singleton
class TrainingDetailFactory @Inject constructor(
    private val trainingDetailPresenterProvider: Provider<FragmentDetailPresenter>
) {
    fun createTrainingDetailPresenter(): FragmentDetailPresenter = trainingDetailPresenterProvider.get()
}


