package com.example.splash.di

import com.example.core.di.CoreComponent
import com.example.splash.di.modules.MainSplashModule
import com.example.splash.ui.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainSplashModule::class], dependencies = [CoreComponent::class])
interface SplashComponent {

    fun inject(activity: SplashActivity)

    @Component.Builder
    interface Builder {

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): SplashComponent
    }


    companion object {
        private var instance: SplashComponent? = null

        fun init(coreComponent: CoreComponent): SplashComponent {
            if(instance == null) {
                instance = DaggerSplashComponent.builder().coreComponent(coreComponent).build()
            }

            return instance!!
        }
    }
}