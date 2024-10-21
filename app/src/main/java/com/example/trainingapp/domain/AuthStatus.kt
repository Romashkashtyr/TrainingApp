package com.example.trainingapp.domain

sealed class AuthStatus<T> {
    data class Success<T>(val info: T): AuthStatus<T>()
    data class NoNetwork<T>(val statusMessage: String): AuthStatus<T>()
    data class Failure<T>(val statusMessage: String): AuthStatus<T>()
}