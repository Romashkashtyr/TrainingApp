package com.example.trainings.ui

import com.example.trainingapp.data.repository.TrainingsRepositoryImpl
import com.example.trainingapp.domain.repository.FitnessResultRepositoryImpl
import com.example.core.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter @Inject constructor(
    val repository: TrainingsRepositoryImpl,
    val repositoryResult: FitnessResultRepositoryImpl
): BasePresenter<TrainingsView>() {



//    @Inject
//    lateinit var repository: TrainingsRepositoryImpl
//
//    @Inject
//    lateinit var repositoryResult: FitnessResultRepositoryImpl

    fun requestTrainingList(){
        launch {
            val list = repository.requestTrainingList()
            withContext(Dispatchers.Main){
                viewState.showTrainingsList(list)
            }
        }
    }

    suspend fun getWorkoutSessions(

    ) {
        repositoryResult.getWorkoutSessions(count = 10, next = null, previous = null, result = emptyList())
        TODO()
    }

}