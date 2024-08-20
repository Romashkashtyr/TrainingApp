package com.example.trainingapp.start.signup

import com.example.trainingapp.domain.usecases.FirebaseAuthRepositoryImpl
import com.example.trainingapp.presentation.base.BasePresenter
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState

@InjectViewState
class SignUpPresenter(firebaseAuth: FirebaseAuth): BasePresenter<ViewSignUp>() {


    private val firebaseRepositoryImpl = FirebaseAuthRepositoryImpl(firebaseAuth)

    fun signUp(email:String, password: String, confirmPassword: String){
        firebaseRepositoryImpl.signUp(email, password, confirmPassword)
    }

}