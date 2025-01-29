package com.example.trainingapp.presentation.base.trainings

import com.example.trainingapp.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TrainingsView: BaseView {

    fun showTrainingsList()
}