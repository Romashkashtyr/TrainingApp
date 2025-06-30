package com.example.trainingapp.presentation

import android.app.Application
import com.example.trainingapp.domain.di.ApplicationComponent
import com.example.trainingapp.domain.di.DaggerApplicationComponent
import com.google.firebase.FirebaseApp

class TrainingApp : Application() {

    companion object {

        lateinit var component: ApplicationComponent

    }




    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .context(applicationContext)
            .build()
        FirebaseApp.initializeApp(this)

//        component = DaggerApplicationComponent.builder()
//            .appModule(AppModule(this))
//            .build()

    }


}