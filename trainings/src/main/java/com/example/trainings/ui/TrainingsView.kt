package com.example.trainings.ui

import com.example.core.base.BaseView
import com.example.trainings.Training
import com.example.trainings.data.response.Exercise
import kotlinx.coroutines.flow.Flow
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TrainingsView : BaseView {

    fun showLoading()

    fun stopLoading()

    //fun showExercises(exercises: Exercise)

    fun showExercises(exercises: List<Exercise>)
}