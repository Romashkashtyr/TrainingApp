package com.example.main.di

import com.example.core.di.CoreComponent
import com.example.main.di.modules.MainModule
import com.example.main.ui.MainActivity
import dagger.Component
import java.lang.IllegalStateException
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class], dependencies = [CoreComponent::class])
interface MainComponent {

    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)

    companion object {
        private var instance: MainComponent? = null

        fun init(coreComponent: CoreComponent): MainComponent {
            if (instance == null) {
                instance = DaggerMainComponent.builder().coreComponent(coreComponent).build()
            }

            return instance!!
        }

        fun getMainInstance(): MainComponent {
            return if (instance != null) instance!! else throw IllegalStateException()
        }
    }
}