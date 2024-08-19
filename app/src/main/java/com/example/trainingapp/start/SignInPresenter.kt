package com.example.trainingapp.start

import com.example.trainingapp.presentation.base.BasePresenter
import com.example.trainingapp.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
open class SignInPresenter(private val firebaseAuthRepository: FirebaseAuthRepository) : BasePresenter<MPVViewSignIn>() {



    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewState?.showViewProgress()
            firebaseAuthRepository.signIn(email, password)
                viewState?.hideViewProgress()

                    viewState?.showError(
                        task.exception?.message ?: "Sign in failed! Enter a valid data"
                    )
                }
            }
        }
    }


}