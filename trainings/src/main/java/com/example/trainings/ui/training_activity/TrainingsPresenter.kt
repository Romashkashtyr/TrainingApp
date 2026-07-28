package com.example.trainings.ui.training_activity

import android.util.Log
import com.example.core.base.BasePresenter
import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.usecases.GetCombineDataAndImageTrainings
import com.example.trainings.domain.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class TrainingsPresenter @Inject constructor(
    private val useCase: GetCombineDataAndImageTrainings,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : BasePresenter<TrainingsView>() {

    private var allExercises = emptyList<FullExercise>()
    private var currentQuery: String = ""

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadExercises()
    }

    private fun withLoad(job: suspend () -> Unit) {
        launch {
            viewState.showLoading()
            try {
                job()
            } finally {
                viewState.stopLoading()
            }
        }
    }

    fun loadExercises() {
        Log.d("TrainingsDebug", "loadExercises() запущен")
        launch {
            viewState.showLoading()
            try {
                val data = useCase.invoke()
                allExercises = data
                Log.d("TrainingsDebug", "Презентер получил ${data.size} элементов")
                onMainThread {
                    applyFilter()
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                Log.e("TrainingsDebug", "Ошибка в презентере: ${e.message}", e)
                onMainThread {
                    viewState.showToast(e.message ?: "Error")
                }
            } finally {
                viewState.stopLoading()
            }
        }
    }

    fun search(query: String) {
        currentQuery = query
        applyFilter()
    }

    private fun applyFilter() {
        val filtered = if (currentQuery.isBlank()) {
            allExercises
        } else {
            allExercises.filter {
                it.name?.contains(currentQuery, ignoreCase = true) == true ||
                        it.primaryMuscles.any { muscle ->
                            muscle.name.contains(currentQuery, ignoreCase = true)
                        }
            }
        }
        viewState.showExercises(filtered)
    }

    fun onFavoriteClicked(exercise: FullExercise) {
        withLoad {
            try {
                toggleFavoriteUseCase(exercise)
                val updatedExercises = useCase.invoke()
                allExercises = updatedExercises
                onMainThread {
                    applyFilter()
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                onMainThread {
                    viewState.showToast(e.message ?: "Error")
                }
            }
        }
    }
}
