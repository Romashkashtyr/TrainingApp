package com.example.core.navigation

import android.content.Context

sealed class Screens(fromContext: Context) {
    data class Main(val fromContext: Context): Screens(fromContext)
    data class TrainingNav(val fromContext: Context): Screens(fromContext)
}