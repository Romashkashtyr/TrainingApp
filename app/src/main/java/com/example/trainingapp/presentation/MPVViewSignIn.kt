package com.example.trainingapp.presentation

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MPVViewSignIn {

    interface View{
        fun showViewProgress()
        fun hideViewProgress()
        fun navigateToHome()
        fun showError(message: String)
    }

    interface SignInLogicPresenter{
        fun signIn(email: String, password: String)
    }



}