package com.example.trainings.ui


import com.example.core.base.BasePresenter
import com.example.trainings.data.response.TrainingResponse
import com.example.trainings.domain.FitnessRepositoryResult
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter @Inject constructor(
    private val repository: TrainingsRepository,
    private val repositoryResult: FitnessRepositoryResult
) : BasePresenter<TrainingsView>() {


//    fun requestTrainingList() {
//        launch {
//            val list = repository.requestTrainingList()
//            withContext(Dispatchers.Main) {
//                viewState.showTrainingsList(list)
//            }
//        }
//    }

    val state: StateFlow<>

    suspend fun observeVideo() {
        val urls = repository.observeTrainingResponse()
            .map { response ->
                response.videoResultTraining
                    .mapNotNull { it.videoUrl }
            }
            .flowOn(Dispatchers.Main)

        return urls
            .map { it }
            .collect {
                viewState.showVideoTraining(it)
            }
    }


    fun observeWorkout(): Flow<TrainingResponse> {
        return repository.observeTrainingResponse()
            .onEach { workout ->
                workout.workouts.map { it.results }
            }
            .flowOn(Dispatchers.Main)
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