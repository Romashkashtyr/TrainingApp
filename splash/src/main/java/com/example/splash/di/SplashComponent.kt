package com.example.splash.di

import android.content.Context
import com.example.core.di.CoreComponent
import com.example.splash.ui.SplashActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton


@Subcomponent
//@Component(modules = [MainSplashModule::class])
interface SplashComponent {

    fun inject(activity: SplashActivity)

//    @Component.Builder
//    interface Builder {
//
//        fun authComponent(authComponent: AuthComponent): Builder
//
//        fun build(): SplashComponent
//    }

    @Subcomponent.Factory
    interface SplashFactorySub {
        fun create(): SplashComponent
    }


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