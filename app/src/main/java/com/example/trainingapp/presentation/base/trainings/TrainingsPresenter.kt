package com.example.trainingapp.presentation.base.trainings

import com.example.trainingapp.data.TrainingsRepositoryImpl
import com.example.trainingapp.domain.Training
import com.example.trainingapp.presentation.base.BasePresenter
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter: BasePresenter<TrainingsView>() {

    @Inject
    lateinit var repository: TrainingsRepositoryImpl

    suspend fun getTrainingList(): ArrayList<Training> {
        val list= async {
            repository.getTrainingList()
        }
        return list.await()
    }

}