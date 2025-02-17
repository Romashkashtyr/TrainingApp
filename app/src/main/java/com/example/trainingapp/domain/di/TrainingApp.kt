package com.example.trainingapp.domain.di

import android.app.Application

class TrainingApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.create()
    }
}