package com.example.core.base


import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface BaseFragmentView: BaseView {

    fun showToastInfo(message: Int)

    fun showToastInfo(message: String)
}