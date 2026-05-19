package com.example.trainings.ui.training_activity


import android.util.Log
import com.example.core.base.BasePresenter
import com.example.trainings.domain.usecases.GetCombineDataAndImageTrainings
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter @Inject constructor(
    private val useCase: GetCombineDataAndImageTrainings
) : BasePresenter<TrainingsView>() {


    fun loadExercises() {
        Log.d("TrainingsDebug", "loadExercises() вызван в презентере")
        withLoad {
            try {
                val data = useCase.invoke()
                Log.d("TrainingsDebug", "repository.loadExercises() вернул элементы")
                withContext(Dispatchers.Main) {
                    Log.d("TrainingsDebug", "Переключились на Main thread")
                    viewState.showExercises(data)
                    Log.d("TrainingsDebug", "showExercises() успешно вызван")
                }
            } catch (e:Exception) {
                if (e is CancellationException) {
                    throw  e
                }
                Log.e("TrainingsDebug", "Ошибка в loadExercises: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    viewState.showToast(e.message ?: "Unknown error")
                }
            } finally {
                withContext(Dispatchers.Main) {
                    viewState.stopLoading()
                    Log.d("TrainingsDebug", "Loading завершен")
                }
            }

        }
    }

//    fun loadExercises() {
//        Log.d("TrainingsDebug", "loadExercises() вызван в презентере")
//        withLoad {
//            try {
//                Log.d("TrainingsDebug", "showLoading() вызван")
//                val data = repository.loadExercises()
//                Log.d("TrainingsDebug", "repository.loadExercises() вернул элементы")
//                withContext(Dispatchers.Main) {
//                    Log.d("TrainingsDebug", "Переключились на Main thread")
//                    viewState.showExercises(data)
//                    Log.d("TrainingsDebug", "showExercises() успешно вызван")
//                }
//            } catch (e: Exception) {
//                if (e is CancellationException) {
//                    throw e
//                }
//                Log.e("TrainingsDebug", "Ошибка в loadExercises: ${e.message}", e)
//                withContext(Dispatchers.Main) {
//                    viewState.showToast(e.message.toString())
//                }
//            }
//        }
//
//    }



    private fun withLoad(job: suspend () -> Unit) {
        viewState.showLoading()
        launch { job() }
        viewState.stopLoading()
    }

}