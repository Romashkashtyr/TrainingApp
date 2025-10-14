package com.example.domain.repository

import com.example.domain.model.Status


interface AuthRepository {


    suspend fun signIn(email: String, password: String): Status<Boolean>

    suspend fun signUp(email: String, password: String, confirmPassword: String): Status<Boolean>

    fun signOut()

    fun isUserLoggedIn(): Boolean

    fun setUserLoggedIn(isLoggedIn: Boolean)

}