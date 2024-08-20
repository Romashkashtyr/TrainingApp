package com.example.trainingapp.start.signin

import com.example.trainingapp.domain.usecases.FirebaseAuthRepositoryImpl
import com.example.trainingapp.presentation.base.BasePresenter
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState

@InjectViewState
open class SignInPresenter(firebaseAuth: FirebaseAuth) : BasePresenter<MPVViewSignIn>() {



    private val firebaseAuthRepository = FirebaseAuthRepositoryImpl(firebaseAuth)

    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewState?.showViewProgress()
            firebaseAuthRepository.signIn(email, password)
            viewState?.hideViewProgress()
        
            }

        }

    fun signOut(){
        firebaseAuthRepository.signOut()
    }

}




