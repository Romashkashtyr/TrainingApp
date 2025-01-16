package com.example.trainingapp.presentation.base.base

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface BaseView : MvpView {

    fun showToast(message: Int)
}