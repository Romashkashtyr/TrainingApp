package com.example.trainings.ui.fragment_favorites

import com.example.core.base.BasePresenter
import com.example.trainings.domain.usecases.ObserveFavoriteExercisesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState

@InjectViewState
class FavoritesPresenter(
    private val observeFavoriteExercisesUseCase: ObserveFavoriteExercisesUseCase
) : BasePresenter<FavoritesView>() {

    private var observeJob: Job? = null

    fun observeFavorites() {
        observeJob?.cancel()
        observeJob = launch {
            observeFavoriteExercisesUseCase()
                .collect { exercises ->
                    onMainThread {
                        viewState.showExercises(exercises)
                    }
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        observeJob?.cancel()
    }
}