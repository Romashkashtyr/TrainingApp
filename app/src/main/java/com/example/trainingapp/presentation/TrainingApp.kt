package com.example.trainingapp.presentation

import android.app.Application
import com.example.trainingapp.domain.di.ApplicationComponent
import com.example.trainingapp.domain.di.DaggerApplicationComponent

class TrainingApp : Application() {

    companion object {

        lateinit var component: ApplicationComponent

    }




    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.create()
//        component = DaggerApplicationComponent.builder()
//            .appModule(AppModule(this))
//            .build()
    }


}