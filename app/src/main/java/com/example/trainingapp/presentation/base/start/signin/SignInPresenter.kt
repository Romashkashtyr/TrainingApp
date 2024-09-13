package com.example.trainingapp.presentation.base.start.signin

import com.example.trainingapp.R
import com.example.trainingapp.data.AuthRepositoryImpl
import com.example.trainingapp.presentation.base.BasePresenter
import com.example.trainingapp.domain.AuthStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
open class SignInPresenter() : BasePresenter<SignInView>() {



    private val firebaseAuthRepository = AuthRepositoryImpl()

    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewState?.showViewProgress()

            CoroutineScope(Dispatchers.IO).launch {
                val success = firebaseAuthRepository.signIn(email,password)
                withContext(Dispatchers.Main) {
                    when (success) {
                        AuthStatus.Success("") -> viewState.showToast(R.string.sign_in_success.toString())
                        AuthStatus.Failure("") -> viewState.showToast(R.string.sign_in_failure.toString())
                        AuthStatus.NoNetwork("") -> viewState.showToast(R.string.network_failure.toString())
                        else -> viewState.showToast("")
                    }
                    TODO()
                }

                viewState.hideViewProgress()
            }

            }

        }

    suspend fun signUp(email:String, password: String, confirmPassword: String){
        firebaseAuthRepository.signUp(email, password, confirmPassword)
    }

    fun signOut(){
        firebaseAuthRepository.signOut()
    }

}




