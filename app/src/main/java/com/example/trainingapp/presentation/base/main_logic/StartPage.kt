package com.example.trainingapp.presentation.base.main_logic

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface StartPage : MvpView {

    fun addWater(waterCount: Int)
}