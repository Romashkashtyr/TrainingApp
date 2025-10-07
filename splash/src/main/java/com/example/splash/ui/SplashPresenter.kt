package com.example.splash.ui

import com.example.trainingapp.data.repository.AuthRepositoryImpl
import moxy.MvpPresenter
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val authRepository: AuthRepositoryImpl
): MvpPresenter<SplashView>() {


     fun checkAuthorization() {
        viewState.showProgress()
            try {
                val isLoggedIn = authRepository.isUserLoggedIn()
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