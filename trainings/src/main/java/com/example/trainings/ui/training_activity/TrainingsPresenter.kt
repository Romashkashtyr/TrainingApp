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

    private var observeJob: Job? = null
    fun loadExercises() {
        Log.d("TrainingsDebug", "loadExercises() вызван в презентере")
        withLoad {
            try {
                val data = useCase.invoke()
                Log.d("TrainingsDebug", "repository.loadExercises() вернул элементы")
                onMainThread{
                    Log.d("TrainingsDebug", "Переключились на Main thread")
                    viewState.showExercises(data)
                    Log.d("TrainingsDebug", "showExercises() успешно вызван")
                }
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
                Log.e("TrainingsDebug", "Ошибка в loadExercises: ${e.message}", e)
                onMainThread {
                    viewState.showToast(e.message ?: "Unknown error")
                }
            } finally {
                onMainThread {
                    viewState.stopLoading()
                    Log.d("TrainingsDebug", "Loading завершен")
                }
            }

        }
    }


//    fun loadFavorites() {
//        launch {
//            try {
//                val exercises = getFavoriteUseCase.invoke()
//
//                onMainThread {
//                    viewState.showExercises(exercises)
//                }
//            } catch (e: Exception) {
//                onMainThread {
//                    viewState.showToast(e.message ?: "Error")
//                }
//            }
//        }
//    }

    fun onFavoriteClicked(exercise: FullExercise) {
        launch {
            try {
                toggleFavoriteUseCase(exercise)

                val updatedExercises = useCase.invoke()

                onMainThread {
                    viewState.showExercises(updatedExercises)
                }

            } catch (e: Exception) {
                onMainThread {
                    viewState.showToast(e.message ?: "Error")
                }
            }
        }
    }

    private fun withLoad(job: suspend () -> Unit) {
        viewState.showLoading()
        launch { job() }
        viewState.stopLoading()
    }

}