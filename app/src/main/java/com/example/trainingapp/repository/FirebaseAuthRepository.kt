package com.example.trainingapp.repository

import com.example.trainingapp.start.FirebaseAuthStatus


interface FirebaseAuthRepository{


    suspend fun signIn(email: String, password: String): FirebaseAuthStatus

    suspend fun signUp(email: String, password: String, confirmPassword: String): FirebaseAuthStatus

    fun signOut()

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