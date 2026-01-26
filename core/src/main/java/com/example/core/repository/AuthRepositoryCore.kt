package com.example.core.repository

import com.example.core.structures.Status


interface AuthRepositoryCore {


    suspend fun signIn(email: String, password: String): Status<Boolean>

    suspend fun signUp(email: String, password: String, confirmPassword: String): Status<Boolean>

    fun signOut()

    fun isUserLoggedIn(): Boolean

    fun setUserLoggedIn(isLoggedIn: Boolean)

}