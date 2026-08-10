package com.example.main.ui.steps_fragment

import com.example.core.base.BaseFragmentView
import com.example.main.data.entitieModules.Steps
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface StepsView: BaseFragmentView {

    fun showTodaySteps(steps: Int)

    fun showStepsHistory(history: List<Steps>)

    fun showLoading()

    fun stopLoading()


}