package com.example.core.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.core.Constants.IS_LOGGED_IN
import com.example.core.repository.CheckAuthRepositoryCore
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class CheckAuthRepositoryCoreImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val sharedPreferences: SharedPreferences
) : CheckAuthRepositoryCore {

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
        sharedPreferences.edit { putBoolean(IS_LOGGED_IN, isLoggedIn) }
    }

}