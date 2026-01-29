package com.example.auth.repository

import com.example.core.structures.Status


interface AuthRepository {


    suspend fun signIn(email: String, password: String): Status<Boolean>

    suspend fun signUp(email: String, password: String, confirmPassword: String): Status<Boolean>


}