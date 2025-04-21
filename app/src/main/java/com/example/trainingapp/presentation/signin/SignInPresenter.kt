package com.example.trainingapp.presentation.signin


import com.example.trainingapp.R
import com.example.trainingapp.data.repository.AuthRepositoryImpl
import com.example.trainingapp.domain.Status
import com.example.trainingapp.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
open class SignInPresenter : BasePresenter<SignInView>() {



    private val authRepository = AuthRepositoryImpl()

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
                        }
                    }
                    viewState?.hideViewProgress()
                }

            }

        }
    }



    fun signUp(email: String, password: String, confirmPassword: String) {
        launch {
            authRepository.signUp(email, password, confirmPassword)
        }

    }


    fun requestChangeMode() {
        viewState.changeAuthMode()
    }

}




