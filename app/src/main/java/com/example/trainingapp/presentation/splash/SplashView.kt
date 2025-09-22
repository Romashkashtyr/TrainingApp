package com.example.trainingapp.presentation.splash

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface SplashView: MvpView {

    @AddToEndSingle
    fun showProgress()

    @AddToEndSingle
    fun hideProgress()

    @AddToEndSingle
    fun navigateToMain()

    @AddToEndSingle
    fun navigateToAuthorization()

    @AddToEndSingle
    fun showError(message:String)
}