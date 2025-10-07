package com.example.splash.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SplashView: MvpView {


    fun showProgress()

    fun hideProgress()

    fun navigateToMain()

    fun navigateToAuthorization()

    fun showError(message:String)
}