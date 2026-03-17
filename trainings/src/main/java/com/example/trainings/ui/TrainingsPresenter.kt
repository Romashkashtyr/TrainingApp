package com.example.trainings.ui


import com.example.core.base.BasePresenter
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter @Inject constructor(
    private val repository: TrainingsRepository
) : BasePresenter<TrainingsView>() {

    suspend fun loadExercises() {
        launch {
            viewState.showLoading()
            try {
                val data = repository.loadExercises()
                viewState.showTrainingsList()
            }
        }
    }

}