package com.example.splash.di

import android.content.Context
import dagger.Component


@Component(modules = [MainSplashModule::class])
abstract class SplashComponent {


    companion object {
        private var splashComponent: SplashComponent? = null
        fun init(context: Context): SplashComponent {
            if(splashComponent == null) {
                splashComponent = DaggerSplashComponent.create()
            }

            return splashComponent!!
        }
    }
}