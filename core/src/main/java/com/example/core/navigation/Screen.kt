package com.example.core.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

sealed class Screen {

    data class Auth(val fromContext: Activity) : Screen()
    data class Main(val fromContext: Activity) : Screen()
    data class TrainingNav(val fromContext: Activity) : Screen()
    data class TrainingFav(val fromContext: Activity, val containerId: Int) : Screen()
    data class WaterFrag(val fromContext: Activity, val containerId: Int) : Screen()
}