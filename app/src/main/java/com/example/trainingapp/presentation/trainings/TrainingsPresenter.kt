package com.example.trainingapp.presentation.trainings

import com.example.trainingapp.data.repository.TrainingsRepositoryImpl
import com.example.trainingapp.domain.di.TrainingApp
import com.example.trainingapp.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class TrainingsPresenter: BasePresenter<TrainingsView>() {

    init {
        TrainingApp.component.inject(this)
    }

    @Inject
    lateinit var repository: TrainingsRepositoryImpl

    fun requestTrainingList(){
        launch {
            val list = repository.requestTrainingList()
            withContext(Dispatchers.Main){
                viewState.showTrainingsList(list)
            }
        }
    }

}