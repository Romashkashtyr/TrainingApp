package com.example.core.navigation

import android.content.Context
import android.content.Intent

sealed class Screen(fromContext: Context) {

    //abstract fun createIntent(context: Context): Intent

    data class Main(val fromContext: Context): Screen(fromContext)
    data class TrainingNav(val fromContext: Context): Screen(fromContext)
    data class AuthorizationNav(val fromContext: Context): Screen(fromContext)
}