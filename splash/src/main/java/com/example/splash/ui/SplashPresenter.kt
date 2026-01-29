package com.example.splash.ui

import com.example.core.repository.CheckAuthRepositoryCore
import moxy.MvpPresenter
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val checkAuthRepositoryCore: CheckAuthRepositoryCore
) : MvpPresenter<SplashView>() {


    fun checkAuthorization() {
        viewState.showProgress()
        try {
            val isLoggedIn = checkAuthRepositoryCore.isUserLoggedIn()
            if (isLoggedIn) {
                viewState.navigateToMain()
            } else {
                viewState.navigateToAuthorization()
            }
        } catch (e: Exception) {
            viewState.showError("Not checking authorization: ${e.message}")
        } finally {
            viewState.hideProgress()
        }
    }
}