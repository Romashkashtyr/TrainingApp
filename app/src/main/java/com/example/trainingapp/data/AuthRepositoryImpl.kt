package com.example.trainingapp.data

import com.example.trainingapp.domain.Status
import com.example.trainingapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signIn(email: String, password: String): Status<String> {

        return ExceptionCatcher().launchWithCatch {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result.user != null) {
               return@launchWithCatch Status.Success("Success") // Уточнить
            } else {
                return@launchWithCatch Status.Failure("Failure") // Уточнить
            }

        }
    }


    override suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): Status<String> {
        if (password != confirmPassword) {
            return Status.Failure("Passwords do not match")
        }
        return ExceptionCatcher().launchWithCatch {
            val resultSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (resultSignUp.user != null) {
               return@launchWithCatch Status.Success("Success")
            } else {
              return@launchWithCatch  Status.Failure("Failure")
            }
        }

    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}


