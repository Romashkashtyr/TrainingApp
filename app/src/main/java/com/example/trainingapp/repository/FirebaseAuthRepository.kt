package com.example.trainingapp.repository


import com.example.trainingapp.start.SignInPresenter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

interface FirebaseAuthRepository{


    fun signIn(email: String, password: String)

//    fun signIn(email: String, password: String) {
//        if (email.isNotEmpty() && password.isNotEmpty()) {
//            signInPresenter.viewState?.showViewProgress()
//            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
//                signInPresenter.viewState?.hideViewProgress()
//                if (task.isSuccessful) {
//                    signInPresenter.viewState?.navigateToHome()
//                } else {
//                    signInPresenter.viewState?.showError(
//                        task.exception?.message ?: "Sign in failed! Enter a valid data"
//                    )
//                }
//            }
//
//        }
//    }
}