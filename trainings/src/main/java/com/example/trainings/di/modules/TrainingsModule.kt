package com.example.trainings.di.modules

import com.example.trainings.ui.training_activity.TrainingsPresenter
import com.example.trainings.domain.usecases.GetCombineDataAndImageTrainings
import com.example.trainings.domain.usecases.GetExerciseByIdUseCase
import com.example.trainings.domain.usecases.ToggleFavoriteUseCase
import com.example.trainings.domain.usecases.UpdateFavoriteUseCase
import com.example.trainings.ui.fragment_detail_training.FragmentDetailPresenter
import com.example.trainings.ui.fragment_favorites.FavoritesPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
class TrainingsModule {

    @Provides
    fun provideTrainingPresenterFactory(
        getCombineDataAndImageTrainings: GetCombineDataAndImageTrainings,
        toggleFavoriteUseCase: ToggleFavoriteUseCase
    ): TrainingsPresenter {
        return TrainingsPresenter(getCombineDataAndImageTrainings, toggleFavoriteUseCase)
    }

    @Provides
    fun provideFragmentDetailPresenterFactory(
        getExerciseByIdUseCase: GetExerciseByIdUseCase,
        toggleFavoriteUseCase: ToggleFavoriteUseCase,
        updateFavoriteUseCase: UpdateFavoriteUseCase
    ): FragmentDetailPresenter {
        return FragmentDetailPresenter(
            getExerciseByIdUseCase,
            toggleFavoriteUseCase,
            updateFavoriteUseCase
        )
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
    fun createTrainingDetailPresenter(): FragmentDetailPresenter =
        trainingDetailPresenterProvider.get()
}

@Singleton
class FavoritesFactory @Inject constructor(
    private val presenter: Provider<FavoritesPresenter>
) {
    fun createFavoritesPresenter(): FavoritesPresenter {
        return presenter.get()
    }
}


