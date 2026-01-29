package com.example.auth.data

import android.util.Log
import com.example.auth.repository.AuthRepository
import com.example.core.exception.ExceptionCatcher
import com.example.core.repository.CheckAuthRepositoryCore
import com.example.core.structures.Status
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val catcher: ExceptionCatcher,
    private val checkAuthRepositoryCore: CheckAuthRepositoryCore
) : AuthRepository {


    override suspend fun signIn(email: String, password: String): Status<Boolean> {
        return catcher.launchWithCatch {
            Log.d("AuthRepository", "Attempting signIn with email: $email")
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result.user != null) {
                Log.d("AuthRepository", "SignIn successful, user: ${result.user?.uid}")
                checkAuthRepositoryCore.setUserLoggedIn(true)
                return@launchWithCatch Status.Success(true)
            } else {
                Log.w("AuthRepository", "SignIn failed: user is null")
                return@launchWithCatch Status.Failure("User not found or invalid credentials")
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
                checkAuthRepositoryCore.setUserLoggedIn(true)
                return@launchWithCatch Status.Success(true)
            } else {
                return@launchWithCatch Status.Failure("Failed to create user")
            }
        }
    }


}


