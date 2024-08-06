package com.example.trainingapp.start

import com.example.trainingapp.presentation.base.BasePresenter
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
open class SignInPresenter(private val firebaseAuth: FirebaseAuth) : BasePresenter<MPVViewSignIn>() {



    fun signIn(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()){
            viewState?.showViewProgress()
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                viewState?.hideViewProgress()
                if (task.isSuccessful){
                    viewState?.navigateToHome()
                } else {
                    viewState?.showError(task.exception?.message ?: "Sign in failed! Enter a valid data")
                }
            }

        }
    }


}