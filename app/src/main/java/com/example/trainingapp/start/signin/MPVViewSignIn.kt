package com.example.trainingapp.start.signin

import com.example.trainingapp.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MPVViewSignIn : BaseView {

        fun showViewProgress()
        fun hideViewProgress()
        fun navigateToHome()
        fun showError(message: String)

}