package com.example.main.ui.workout_fragment

import com.example.core.base.BaseFragmentView
import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface WorkoutView : BaseFragmentView {

    fun showError(message: String)

    fun openWorkout(level: WorkoutLevel, type: WorkoutType)

}