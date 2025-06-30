package com.example.trainingapp.presentation

import android.app.Application
import com.example.trainingapp.domain.di.ApplicationComponent
import com.example.trainingapp.domain.di.DaggerApplicationComponent
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class TrainingApp : Application() {


    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        component = DaggerApplicationComponent.builder()
            .context(this)
            .build()


//        component = DaggerApplicationComponent.builder()
//            .appModule(AppModule(this))
//            .build()

    }

    companion object {

        lateinit var component: ApplicationComponent

    }


}