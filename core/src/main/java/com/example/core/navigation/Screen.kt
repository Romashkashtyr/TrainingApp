package com.example.core.navigation

import android.content.Context

sealed class Screen(fromContext: Context) {
    data class Main(val fromContext: Context): Screen(fromContext)
    data class TrainingNav(val fromContext: Context): Screen(fromContext)
    data class AuthorizationNav(val fromContext: Context): Screen(fromContext)
}