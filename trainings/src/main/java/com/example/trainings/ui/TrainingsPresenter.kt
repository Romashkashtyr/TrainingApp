package com.example.trainings.ui


import com.example.core.base.BasePresenter
import com.example.trainings.domain.FitnessRepositoryResult
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter @Inject constructor(
    private val repository: TrainingsRepository,
    private val repositoryResult: FitnessRepositoryResult
) : BasePresenter<TrainingsView>() {


    fun requestTrainingList() {
        launch {
            val list = repository.requestTrainingList()
            withContext(Dispatchers.Main) {
                viewState.showTrainingsList(list)
            }
        }
    }

    suspend fun getWorkoutSessions(

    ) {
        repositoryResult.getWorkoutSessions(
            count = 10,
            next = null,
            previous = null,
            result = emptyList()
        )
        TODO()
    }

}