package com.example.trainingapp.data

import com.example.trainingapp.domain.AuthStatus
import com.example.trainingapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signIn(email: String, password: String): AuthStatus {

        return ExceptionCatcher().launchCatcher {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result.user != null) {
                AuthStatus.Success("Success") // Уточнить
            } else {
                AuthStatus.Failure("Failure") // Уточнить
            }

        }
    }


    override suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): AuthStatus {
        if (password != confirmPassword) {
            return AuthStatus.Failure("Passwords do not match")
        }
        return ExceptionCatcher().launchCatcher {
            val resultSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (resultSignUp.user != null) {
                AuthStatus.Success("Success") // Уточнить
            } else {
                AuthStatus.Failure("Failure") // Уточнить
            }
        }

    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}


