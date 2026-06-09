package com.example.core.di

import android.content.Context
import android.content.SharedPreferences
import com.example.core.di.modules.CoreModule
import com.example.core.di.modules.ExceptionModule
import com.example.core.di.modules.RouterModule
import com.example.core.navigation.Router
import com.example.core.repository.CheckAuthRepositoryCore
import com.google.firebase.auth.FirebaseAuth
import dagger.BindsInstance
import dagger.Component


@Component(modules = [CoreModule::class, ExceptionModule::class, RouterModule::class])
interface CoreComponent {

    fun context(): Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): CoreComponent

    }

    fun authRepositoryCore(): CheckAuthRepositoryCore

    fun provideRouter(): Router

    fun provideFirebaseAuth(): FirebaseAuth

    fun provideSharedPreferences(): SharedPreferences

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