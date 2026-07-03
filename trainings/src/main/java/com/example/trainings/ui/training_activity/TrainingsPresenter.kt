package com.example.trainings.ui.training_activity


import android.util.Log
import com.example.core.base.BasePresenter
import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.usecases.GetCombineDataAndImageTrainings
import com.example.trainings.domain.usecases.ObserveFavoriteExercisesUseCase
import com.example.trainings.domain.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter @Inject constructor(
    private val useCase: GetCombineDataAndImageTrainings,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : BasePresenter<TrainingsView>() {

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
                Log.d("TrainingsDebug", "Презентер получил ${data.size} элементов, передаю в View")
                // Гарантируем, что список передается в UI
                viewState.showExercises(data.toList()) 
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                Log.e("TrainingsDebug", "Ошибка в презентере: ${e.message}", e)
                viewState.showToast(e.message ?: "Error")
            } finally {
                viewState.stopLoading()
            }
        }
    }

    fun onFavoriteClicked(exercise: FullExercise) {
        withLoad {
            try {
                toggleFavoriteUseCase(exercise)
                val updatedExercises = useCase.invoke()
                onMainThread {
                    viewState.showExercises(updatedExercises)
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