package com.example.trainingapp.presentation.authorization


import com.example.trainingapp.R
import com.example.trainingapp.data.repository.AuthRepositoryImpl
import com.example.trainingapp.domain.Status
import com.example.trainingapp.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
open class AuthorizationPresenter @Inject constructor(
    private val authRepository: AuthRepositoryImpl
) : BasePresenter<AuthorizationView>() {


    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewState?.showViewProgress()
            launch {
                val authStatus = authRepository.signIn(email, password)
                withContext(Dispatchers.Main) {
                    when (authStatus) {
                        is Status.Failure -> {
                            viewState.showToast(R.string.sign_in_failure)
                        }

                        is Status.NoNetwork -> {
                            viewState.showToast(R.string.network_failure)
                        }

                        is Status.Success -> {
                            viewState.showToast(R.string.sign_in_success)
                            viewState.navigateToHome()
                        }
                    }
                    viewState?.hideViewProgress()
                }

            }

        } else {
            viewState.showToast(R.string.enter_your_data)
        }
    }


    fun signUp(email: String, password: String, confirmPassword: String) {
        if (email.isEmpty() || password.isEmpty()) {
            viewState.showToast(R.string.sign_in_failure)
            return
        }
        if (password.length < 6) {
            viewState.showToast(R.string.sign_up_weak_password)
            return
        }
        viewState?.showViewProgress()
        launch {
            val authStatus = authRepository.signUp(email, password, confirmPassword)
            withContext(Dispatchers.Main) {
                when (authStatus) {
                    is Status.Failure -> {
                        viewState.showToast(R.string.sign_in_failure)
                    }

                    is Status.NoNetwork -> {
                        viewState.showToast(R.string.network_failure)
                    }

                    is Status.Success -> {
                        viewState.showToast(R.string.sign_in_success)
                        viewState.navigateToHome()
                    }
                }
                viewState?.hideViewProgress()
            }
        }

    }


    fun requestChangeMode() {
        viewState.changeAuthMode()
    }


}




