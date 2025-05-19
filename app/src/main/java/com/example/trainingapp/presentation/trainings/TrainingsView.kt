package com.example.trainingapp.presentation.trainings

import com.example.trainingapp.domain.Training
import com.example.trainingapp.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TrainingsView: BaseView {

    fun showTrainingsList(trainingList: List<Training> )
}