package com.example.trainingapp.presentation.base.start.signin


import com.example.trainingapp.R
import com.example.trainingapp.data.AuthRepositoryImpl
import com.example.trainingapp.domain.Status
import com.example.trainingapp.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
open class SignInPresenter : BasePresenter<SignInView>() {



    private val firebaseAuthRepository = AuthRepositoryImpl()

    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewState?.showViewProgress()

            launch {
                val success = firebaseAuthRepository.signIn(email, password)
                withContext(Dispatchers.Main) {
                    when (success) {
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
            firebaseAuthRepository.signUp(email, password, confirmPassword)
        }

    }

    fun signOut() {
        firebaseAuthRepository.signOut()
    }

    fun requestChangeMode() {
        viewState.changeAuthMode()
    }

}




