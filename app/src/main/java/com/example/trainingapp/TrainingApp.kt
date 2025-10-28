package com.example.trainingapp

import android.app.Application
import android.content.Context
import com.example.auth.domain.di.AuthComponent
import com.example.auth.ui.AuthorizationActivity
import com.example.core.navigation.Router
import com.example.core.navigation.RouterHolder
import com.example.core.navigation.RouterProvider
import com.example.core.navigation.Screen
import com.example.main.ui.MainActivity
import com.example.splash.di.SplashComponent
import com.example.trainingapp.domain.di.ApplicationComponent
import com.example.trainingapp.domain.di.DaggerApplicationComponent
import com.example.trainings.di.TrainingComponent
import com.example.trainings.ui.TrainingsListActivity
import com.google.firebase.FirebaseApp

class TrainingApp : Application(), Router {


    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(this)
        component = DaggerApplicationComponent.builder()
            .context(this)
            .build()
        RouterHolder.router = this
        SplashComponent.init(applicationContext)
        AuthComponent.init(applicationContext)
        TrainingComponent.init(applicationContext)
    }

    companion object {

        lateinit var component: ApplicationComponent
        lateinit var instance: Application

    }

    override fun navigateToAuth(fromContext: Context) {
        fromContext.startActivity(AuthorizationActivity.getIntent(fromContext))
    }

    override fun navigateTo(screen: Screen) {
        when(screen) {
            is Screen.Main -> screen.fromContext.startActivity(MainActivity.getIntent(screen.fromContext))

            is Screen.TrainingNav -> screen.fromContext.startActivity(TrainingsListActivity.getIntent(screen.fromContext))
        }
    }



}