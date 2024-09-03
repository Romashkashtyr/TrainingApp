package com.example.trainingapp.domain.usecases

import android.app.Application
import com.example.trainingapp.R
import com.example.trainingapp.repository.FirebaseAuthRepository
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class FirebaseAuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : FirebaseAuthRepository {

    override suspend fun signIn(email: String, password: String): Boolean {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val success = result.user?.email != null && result.user?.isEmailVerified != null
        return try {
            success
        } catch (e: FirebaseException) {
            e.message.toString()
                false
        }
    }


    override suspend fun signUp(email: String, password: String, confirmPassword: String): Boolean {
        val resultSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val successOperation = resultSignUp.user?.email != null && resultSignUp.user?.isEmailVerified != null
        return try {
            successOperation
        } catch (e: FirebaseException){
            e.message.toString()
            false
        }



    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}


