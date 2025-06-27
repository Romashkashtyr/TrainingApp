package com.example.trainingapp.presentation.signin

import com.example.trainingapp.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface AuthorizationView : BaseView {

        fun showViewProgress()
        fun hideViewProgress()
        fun navigateToHome()
        fun changeAuthMode()

}