package com.example.main.ui.workout_fragment

import com.example.core.base.BaseFragmentView
import com.example.main.data.entitieModules.WorkoutExercise
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface WorkoutView : BaseFragmentView {

    fun showError(message: String)

}