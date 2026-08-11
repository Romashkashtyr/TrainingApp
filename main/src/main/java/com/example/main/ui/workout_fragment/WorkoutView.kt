package com.example.main.ui.workout_fragment

import com.example.core.base.BaseFragmentView
import com.example.main.data.entitieModules.WorkoutHistory
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface WorkoutView: BaseFragmentView {

    fun showWorkoutHistory(history: List<WorkoutHistory>)

    fun showLoading()

    fun hideLoading()

    fun showEmptyHistory()

    fun hideEmptyHistory()
}