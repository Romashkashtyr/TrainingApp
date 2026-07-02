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
        Log.d("TrainingsDebug", "loadExercises() вызван в презентере")
        withLoad {
            try {
                val data = useCase.invoke()
                Log.d("TrainingsDebug", "repository.loadExercises() вернул ${data.size} элементов")
                onMainThread {
                    viewState.showExercises(data)
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                Log.e("TrainingsDebug", "Ошибка в loadExercises: ${e.message}", e)
                onMainThread {
                    viewState.showToast(e.message ?: "Unknown error")
                }
            }
        }
    }

}