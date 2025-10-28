package com.example.core.navigation

import android.content.Context

interface Router {

    fun navigateToAuth(fromContext: Context)
    fun navigateTo(screen: Screen)
}