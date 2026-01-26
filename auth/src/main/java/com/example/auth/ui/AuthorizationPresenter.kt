package com.example.auth.ui


import com.example.auth.R
import com.example.auth.domain.usecase.SignInUseCase
import com.example.auth.domain.usecase.SignUpUseCase
import com.example.core.base.BasePresenter
import com.example.core.structures.Status
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
open class AuthorizationPresenter @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : BasePresenter<AuthorizationView>() {


    fun signIn(email: String, password: String) {

        viewState.showViewProgress()
        launch {
            when (signInUseCase(email, password)) {
                is Status.Failure<*> -> {
                    viewState.showToast(R.string.sign_in_failure)
                }

                is Status.NoNetwork<*> -> {
                    viewState.showToast(R.string.sign_in_failure)
                }

                is Status.Success<*> -> {
                    viewState.showToast(R.string.sign_in_success)
                    viewState.navigateToHome()
                }
            }
        }
    }


    fun signUp(email: String, password: String, confirmPassword: String) {

        viewState.showViewProgress()

        launch {
            when (signUpUseCase(email, password, confirmPassword)) {
                is Status.Failure<*> -> {
                    viewState.showToast(R.string.sign_in_failure)
                }

                is Status.NoNetwork<*> -> {
                    viewState.showToast(R.string.network_failure)
                }

                is Status.Success<*> -> {
                    viewState.showToast(R.string.sign_in_success)
                    viewState.navigateToHome()
                }
            }
        }
    }

    fun requestChangeMode() {
        viewState.changeAuthMode()
    }

}










