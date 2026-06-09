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

        Log.d("AUTH_REPO", "signUp START email=$email")

        if (password != confirmPassword) {
            Log.d("AUTH_REPO", "FAIL: password mismatch")
            return Status.Failure("Passwords do not match")
        }
        return catcher.launchWithCatch {
            Log.d("AUTH_REPO", "Firebase createUser START")
            val resultSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Log.d("AUTH_REPO", "Firebase response received")
            if (resultSignUp.user != null) {
                Log.d("AUTH_REPO", "SUCCESS user=${resultSignUp.user?.uid}")
                checkAuthRepositoryCore.setUserLoggedIn(true)
                return@launchWithCatch Status.Success(true)
            } else {
                Log.d("AUTH_REPO", "FAIL: user == null")
                return@launchWithCatch Status.Failure("Failed to create user")
            }
        }
    }


}


