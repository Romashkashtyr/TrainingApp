package com.example.trainingapp

import android.app.Application
import android.content.Context
import com.example.core.navigation.Router
import com.example.core.navigation.RouterHolder
import com.example.splash.di.SplashComponent
import com.example.trainingapp.domain.di.ApplicationComponent
import com.example.trainingapp.domain.di.DaggerApplicationComponent
import com.example.auth.AuthorizationActivity
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
    }

    companion object {

        lateinit var component: ApplicationComponent
        lateinit var instance: Application

    }

    override fun navigateToAuth(fromContext: Context) {
        fromContext.startActivity(com.example.auth.AuthorizationActivity.getIntent(fromContext))
    }


}