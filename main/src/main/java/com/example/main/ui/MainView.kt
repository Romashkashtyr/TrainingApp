package com.example.main.ui

import com.example.core.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : BaseView {

    fun initListData(waterAmount: Int)
    fun addWater(waterCount: Int)
}