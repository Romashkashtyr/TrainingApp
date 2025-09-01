package com.example.trainingapp.presentation.base

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface BaseView : MvpView {

    fun showToast(message: Int)
    fun showToast(message: String)
}