package com.example.trainingapp.presentation.base.start.signin

import com.example.trainingapp.data.AuthMode
import com.example.trainingapp.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SignInView : BaseView {

        fun showViewProgress()
        fun hideViewProgress()
        fun navigateToHome()
        fun changeAuthMode()

}