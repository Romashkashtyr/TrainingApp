package com.example.splash.ui

import com.example.core.repository.AuthRepositoryCore
import moxy.MvpPresenter
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val authRepositoryCore: AuthRepositoryCore
): MvpPresenter<SplashView>() {


     fun checkAuthorization() {
        viewState.showProgress()
            try {
                val isLoggedIn = authRepositoryCore.isUserLoggedIn()
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