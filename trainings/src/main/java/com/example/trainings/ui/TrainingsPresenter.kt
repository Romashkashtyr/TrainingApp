package com.example.trainings.ui


import com.example.core.base.BasePresenter
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter @Inject constructor(
    private val repository: TrainingsRepository
) : BasePresenter<TrainingsView>() {

    fun loadExercises() {
        withLoad {
            try {
                val data = repository.loadExercises()
                viewState.showExercises(data)
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
                viewState.showToast(e.message.toString())
            }
        }

    }

    private fun withLoad(job: suspend () -> Unit) {
        viewState.showLoading()
        launch { job() }
        viewState.stopLoading()
    }

}