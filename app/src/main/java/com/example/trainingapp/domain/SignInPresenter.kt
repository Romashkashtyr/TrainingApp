package com.example.trainingapp.domain

import android.widget.Toast
import com.example.trainingapp.presentation.MPVViewSignIn
import com.google.firebase.auth.FirebaseAuth

class SignInPresenter(private val firebaseAuth: FirebaseAuth): MPVViewSignIn.SignInLogicPresenter {
    private val view: MPVViewSignIn.View? = null


    override fun signIn(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()){
            view?.showViewProgress()
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                view?.hideViewProgress()
                if (task.isSuccessful){
                    view?.navigateToHome()
                } else {
                    view?.showError(task.exception?.message ?: "Sign in failed! Enter a valid data")
                }
            }

        }
    }


}