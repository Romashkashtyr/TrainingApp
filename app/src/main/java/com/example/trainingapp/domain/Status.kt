package com.example.trainingapp.domain

sealed class Status<T> {
    data class Success<T>(val info: T): Status<T>()
    data class NoNetwork<T>(val statusMessage: String): Status<T>()
    data class Failure<T>(val statusMessage: String): Status<T>()
}