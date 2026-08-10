package com.example.main.di

import com.example.core.di.CoreComponent
import com.example.main.di.modules.MainModule
import com.example.main.service.StepsCounterService
import com.example.main.ui.activity.MainActivity
import com.example.main.ui.steps_fragment.StepsFragment
import com.example.main.ui.water_fragment.WaterFragment
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
    fun inject(service: StepsCounterService)

    fun inject(fragment: WaterFragment)

    fun inject(fragment: StepsFragment)

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