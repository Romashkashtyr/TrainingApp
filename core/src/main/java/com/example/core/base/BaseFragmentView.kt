package com.example.core.base

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface BaseFragmentView: MvpView {

    fun showToastInfo(message: Int)

    fun showToastInfo(message: String)
}