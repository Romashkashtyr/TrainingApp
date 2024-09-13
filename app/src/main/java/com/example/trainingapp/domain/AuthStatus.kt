package com.example.trainingapp.domain

sealed class AuthStatus {
    abstract val statusMessage: String
    data class Success(override val statusMessage: String): AuthStatus()
    data class NoNetwork(override val statusMessage: String): AuthStatus()
    data class Failure(override val statusMessage: String): AuthStatus()
}