package com.example.auth.ui

import com.example.core.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface AuthorizationView : BaseView {

        fun showViewProgress()
        fun hideViewProgress()
        fun navigateToHome()
        fun changeAuthMode()

}