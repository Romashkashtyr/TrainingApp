package com.example.trainingapp.domain.di

import android.app.Application

class TrainingApp : Application() {

    companion object {
        val component by lazy {
            DaggerApplicationComponent.create()
        }



    }



}