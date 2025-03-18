package com.example.trainingapp.presentation

import android.app.Application
import com.example.trainingapp.domain.di.DaggerApplicationComponent

class TrainingApp : Application() {

    companion object {
        val component by lazy {
            DaggerApplicationComponent.builder().build()
        }
    }



}