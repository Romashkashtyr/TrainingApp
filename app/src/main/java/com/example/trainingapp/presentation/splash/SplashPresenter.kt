package com.example.trainingapp.presentation.splash

import com.example.trainingapp.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val authRepository: AuthRepositoryImpl
): MvpPresenter<SplashView>() {

    val scopeMain = CoroutineScope(Dispatchers.Main)

     fun checkAuthorization() {
        viewState.showProgress()
        scopeMain.launch {
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
}