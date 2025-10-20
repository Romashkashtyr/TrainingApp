package com.example.trainingapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.core.Constants.IS_LOGGED_IN
import com.example.core.exception.ExceptionCatcher
import com.example.trainingapp.domain.Status
import com.example.trainingapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val catcher: ExceptionCatcher,
    private val sharedPreferences: SharedPreferences
): AuthRepository {


    override suspend fun signIn(email: String, password: String): Status<Boolean> {
        return catcher.launchWithCatch {
            Log.d("AuthRepository", "Attempting signIn with email: $email")
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result.user != null) {
                Log.d("AuthRepository", "SignIn successful, user: ${result.user?.uid}")
                setUserLoggedIn(true)
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
                setUserLoggedIn(true)
               return@launchWithCatch Status.Success(true)
            } else {
              return@launchWithCatch  Status.Failure("Failed to create user")
            }
        }

    }

    override fun signOut() {
        firebaseAuth.signOut()
        setUserLoggedIn(false)
    }

    override fun isUserLoggedIn(): Boolean {
        val isLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN, false)
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        return isLoggedIn && firebaseUser != null
    }

    override fun setUserLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()
    }

}


