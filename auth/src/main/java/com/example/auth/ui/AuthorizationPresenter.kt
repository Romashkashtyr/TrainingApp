package com.example.auth.ui


import android.util.Log
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
            val signInStatus = signInUseCase(email, password)
            onMainThread {
                when (signInStatus) {
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
    }


    fun signUp(email: String, password: String, confirmPassword: String) {

        Log.d("AUTH_PRESENTER", "signUp called")

        viewState.showViewProgress()

        launch {
            Log.d("AUTH_PRESENTER", "inside coroutine")
            val signUpStatus = signUpUseCase(email, password, confirmPassword)
            Log.d("AUTH_PRESENTER", "result=$signUpStatus")
            onMainThread {
                when (signUpStatus) {
                    is Status.Failure<*> -> {
                        viewState.hideViewProgress()
                        viewState.showToast(R.string.sign_up_failure)
                    }

                    is Status.NoNetwork<*> -> {
                        viewState.hideViewProgress()
                        viewState.showToast(R.string.network_failure)
                    }

                    is Status.Success<*> -> {
                        viewState.showToast(R.string.sign_in_success)
                        viewState.hideViewProgress()
                        viewState.navigateToHome()
                    }
                }
            }

        }
    }

    fun requestChangeMode() {
        viewState.changeAuthMode()
    }
//
    //
    //TODO
}
