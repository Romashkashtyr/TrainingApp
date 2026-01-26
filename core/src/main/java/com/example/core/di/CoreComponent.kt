package com.example.core.di

import android.content.Context
import com.example.core.di.modules.CoreModule
import com.example.core.di.modules.RouterModule
import com.example.core.navigation.Router
import com.example.core.repository.AuthRepositoryCore
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [CoreModule::class, RouterModule::class])
interface CoreComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): CoreComponent

    }

    fun authRepositoryCore(): AuthRepositoryCore


    fun provideRouter(): Router

    companion object {
        private var instance: CoreComponent? = null

        fun init(context: Context): CoreComponent {
            if (instance == null) {
                instance = DaggerCoreComponent.builder().context(context).build()
            }

            return instance!!
        }
    }

}