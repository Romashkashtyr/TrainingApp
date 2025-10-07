package com.example.trainingapp.presentation

import android.app.Application
import android.content.Context
import com.example.core.Router
import com.example.core.RouterHolder
import com.example.trainingapp.domain.di.ApplicationComponent
import com.example.trainingapp.domain.di.DaggerApplicationComponent
import com.example.trainingapp.presentation.authorization.AuthorizationActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class TrainingApp : Application(), Router {


    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(this)
        component = DaggerApplicationComponent.builder()
            .context(this)
            .build()
        RouterHolder.router = this
    }

    companion object {

        lateinit var component: ApplicationComponent
        lateinit var instance: Application

    }

    override fun navigateToAuth(fromContext: Context) {
        fromContext.startActivity(AuthorizationActivity.getIntent(fromContext))
    }


}