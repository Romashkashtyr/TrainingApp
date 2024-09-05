package com.example.trainingapp.start.signin

import com.example.trainingapp.R
import com.example.trainingapp.domain.usecases.FirebaseAuthRepositoryImpl
import com.example.trainingapp.presentation.base.BasePresenter
import com.example.trainingapp.start.FirebaseAuthStatus
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
                    when (success) {
                        FirebaseAuthStatus.SUCCESS -> viewState.showToast(R.string.sign_in_success.toString())
                        FirebaseAuthStatus.UNIDENTIFIED_ERROR -> viewState.showToast(R.string.sign_in_failure.toString())
                        FirebaseAuthStatus.NO_NETWORK -> viewState.showToast(R.string.network_failure.toString())
                    }
                }

                viewState.hideViewProgress()
            }

            }

        }

    fun signOut(){
        firebaseAuthRepository.signOut()
    }

}




