package com.example.core.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent

sealed class Screen {

    //abstract fun createIntent(context: Context): Intent

    data class Auth(val fromContext: Activity): Screen()
    data class Main(val fromContext: Activity): Screen()
    data class TrainingNav(val fromContext: Activity): Screen()
}