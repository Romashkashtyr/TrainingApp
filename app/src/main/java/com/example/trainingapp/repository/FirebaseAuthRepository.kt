package com.example.trainingapp.repository


interface FirebaseAuthRepository{


    suspend fun signIn(email: String, password: String): Boolean

    suspend fun signUp(email: String, password: String, confirmPassword: String): Boolean

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