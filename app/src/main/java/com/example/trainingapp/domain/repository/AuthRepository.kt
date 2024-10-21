package com.example.trainingapp.domain.repository

import android.content.Context
import com.example.trainingapp.domain.AuthStatus


interface AuthRepository {


    suspend fun signIn(email: String, password: String): AuthStatus

    suspend fun signUp(email: String, password: String, confirmPassword: String): AuthStatus

    fun signOut()

}