package com.example.trainingapp.domain.usecases

import android.app.Application
import android.net.http.HttpException
import com.example.trainingapp.R
import com.example.trainingapp.repository.FirebaseAuthRepository
import com.example.trainingapp.start.FirebaseAuthStatus
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class FirebaseAuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : FirebaseAuthRepository {

    override suspend fun signIn(email: String, password: String): FirebaseAuthStatus {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return try {
            if(result.user != null) {
                FirebaseAuthStatus.SUCCESS
            } else {
                FirebaseAuthStatus.UNIDENTIFIED_ERROR
            }
        } catch (e: FirebaseException) {
            e.message.toString()
            FirebaseAuthStatus.NO_NETWORK
        }
    }


    override suspend fun signUp(email: String, password: String, confirmPassword: String): FirebaseAuthStatus {
        val resultSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return try {
            if (resultSignUp.user != null){
                FirebaseAuthStatus.SUCCESS
            } else {
                FirebaseAuthStatus.UNIDENTIFIED_ERROR
            }
        } catch (e: FirebaseException){
            e.message.toString()
            FirebaseAuthStatus.NO_NETWORK
        }



    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}


