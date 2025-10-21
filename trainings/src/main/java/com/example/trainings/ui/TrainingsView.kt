package com.example.trainings.ui

import com.example.trainingapp.domain.training.Training
import com.example.core.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TrainingsView: BaseView {

    fun showTrainingsList(trainingList: List<Training> )
}