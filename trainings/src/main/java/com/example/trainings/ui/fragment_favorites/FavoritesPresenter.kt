package com.example.trainings.ui.fragment_favorites

import com.example.core.base.BasePresenter
import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.usecases.ObserveFavoriteExercisesUseCase
import com.example.trainings.domain.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState

@InjectViewState
class FavoritesPresenter(
    private val observeFavoriteExercisesUseCase: ObserveFavoriteExercisesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : BasePresenter<FavoritesView>() {

    private var observeJob: Job? = null

    private var allExercises = emptyList<FullExercise>()

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

    fun toggleFavorite(exercise: FullExercise) {
        launch {
            toggleFavoriteUseCase(exercise)
        }
    }

    fun search(query: String) {
        val filtered = if (query.isBlank()) {
            allExercises
        } else {
            allExercises.filter {
                if (it.name != null) {
                    it.name.contains(query, ignoreCase = true)
                } else {
                    it.description?.contains(query, ignoreCase = true) ?: false
                }
            }
        }
        viewState.showExercises(filtered)
    }

    override fun onDestroy() {
        super.onDestroy()
        observeJob?.cancel()
    }
}