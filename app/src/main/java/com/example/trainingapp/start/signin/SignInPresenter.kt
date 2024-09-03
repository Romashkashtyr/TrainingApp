package com.example.trainingapp.start.signin

import com.example.trainingapp.R
import com.example.trainingapp.domain.usecases.FirebaseAuthRepositoryImpl
import com.example.trainingapp.presentation.base.BasePresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
open class SignInPresenter(firebaseAuth: FirebaseAuth) : BasePresenter<MPVViewSignIn>() {



    private val firebaseAuthRepository = FirebaseAuthRepositoryImpl(firebaseAuth)

    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewState?.showViewProgress()

            CoroutineScope(Dispatchers.IO).launch {
                val success = firebaseAuthRepository.signIn(email,password)

                withContext(Dispatchers.Main) {
                    if (success) viewState.showToast(R.string.sign_in_success.toString())
                    else viewState.showToast(R.string.sign_in_failure.toString())
                }

                viewState.hideViewProgress()
            }

            }

        }

    fun signOut(){
        firebaseAuthRepository.signOut()
    }

}




