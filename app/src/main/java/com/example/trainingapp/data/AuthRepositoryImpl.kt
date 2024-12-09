package com.example.trainingapp.data

import com.example.trainingapp.domain.Status
import com.example.trainingapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl : AuthRepository {

    val firebaseAuth = FirebaseAuth.getInstance()
    private val catcher = ExceptionCatcher()

    override suspend fun signIn(email: String, password: String): Status<Boolean> {

        return ExceptionCatcher().launchWithCatch {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result.user != null) {
               return@launchWithCatch Status.Success(true)
            } else {
                return@launchWithCatch Status.Failure("Failure")
            }

        }
    }


    override suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): Status<Boolean> {
        if (password != confirmPassword) {
            return Status.Failure("Passwords do not match")
        }
        return catcher.launchWithCatch {
            val resultSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (resultSignUp.user != null) {
               return@launchWithCatch Status.Success(true)
            } else {
              return@launchWithCatch  Status.Failure("Failure")
            }
        }

    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}


