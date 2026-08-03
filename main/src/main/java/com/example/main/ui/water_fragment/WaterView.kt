package com.example.main.ui.water_fragment

import com.example.core.base.BaseFragmentView
import com.example.main.data.entitieModules.Water
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface WaterView: BaseFragmentView {

    fun showTodayWater(amount: Int)

    fun showWaterHistory(history: List<Water>)

    fun showLoading()

    fun stopLoading()


}