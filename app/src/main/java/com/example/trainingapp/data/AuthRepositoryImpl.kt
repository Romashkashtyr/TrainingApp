package com.example.trainingapp.data

import com.example.trainingapp.domain.repository.AuthRepository
import com.example.trainingapp.domain.AuthStatus
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signIn(email: String, password: String): AuthStatus {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return try {
            if(result.user != null) {
                AuthStatus.Success("Success") // Уточнить
            } else {
                AuthStatus.Failure("Failure") // Уточнить
            }
        } catch (e: FirebaseException) {
            e.message.toString()
            AuthStatus.NoNetwork("NoNetwork") // Уточнить
        }
    }


    override suspend fun signUp(email: String, password: String, confirmPassword: String): AuthStatus {
        val resultSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return try {
            if (resultSignUp.user != null){
                AuthStatus.Success("Success") // Уточнить
            } else {
                AuthStatus.Failure("Failure") // Уточнить
            }
        } catch (e: FirebaseException){
            e.message.toString()
            AuthStatus.NoNetwork("NoNetwork") // Уточнить
        }



    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}


