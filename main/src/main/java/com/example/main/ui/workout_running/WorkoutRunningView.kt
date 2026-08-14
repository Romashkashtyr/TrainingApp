package com.example.main.ui.workout_running

import com.example.core.base.BaseFragmentView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import com.example.main.data.entitieModules.WorkoutExercise


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface WorkoutRunningView: BaseFragmentView {

    fun showExercise(
        exercise: WorkoutExercise,
        position: Int,
        total: Int
    )

    fun updateTimer(
        seconds: Int
    )

    fun showWorkoutFinished()

    fun showError(
        message: String
    )
}