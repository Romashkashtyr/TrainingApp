package com.example.main.ui.activity

import com.example.core.base.BaseView
import com.example.main.data.entitieModules.Water
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : BaseView {

    fun initListData(waterAmount: Int)
    fun addWater(waterCount: Int)
    fun updateSteps(steps: Int)

    fun updateWater(amount: Int)

    fun showWaterHistory(history: List<Water>)
}