package com.example.trainingapp.domain.repository

import com.example.trainingapp.domain.Status


interface AuthRepository {


    suspend fun signIn(email: String, password: String): Status<String>

    suspend fun signUp(email: String, password: String, confirmPassword: String): Status<String>

    fun signOut()

}