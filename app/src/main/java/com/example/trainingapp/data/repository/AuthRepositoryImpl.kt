package com.example.trainingapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.trainingapp.data.exception.ExceptionCatcher
import com.example.trainingapp.domain.Status
import com.example.trainingapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val catcher: ExceptionCatcher
): AuthRepository {


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


