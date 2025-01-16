package com.example.trainingapp.presentation.base.main

import com.example.trainingapp.presentation.base.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : BaseView {

    fun initListData(waterAmount: Int)
    fun addWater(waterCount: Int)
}