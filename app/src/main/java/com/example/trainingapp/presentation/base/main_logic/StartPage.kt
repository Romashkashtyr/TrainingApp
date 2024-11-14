package com.example.trainingapp.presentation.base.main_logic

import com.example.trainingapp.presentation.base.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface StartPage : BaseView {

    fun addWater(waterCount: Int)
}