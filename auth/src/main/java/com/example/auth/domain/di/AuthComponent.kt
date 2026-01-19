package com.example.auth.domain.di

import android.content.Context
import com.example.core.di.CoreComponent
import com.example.core.repository.AuthRepository
import com.google.android.datatransport.runtime.dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthModule::class], dependencies = [CoreComponent::class])
interface  AuthComponent {

    fun authRepository(): AuthRepository

    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): AuthComponent

    }

    companion object {

        private var authComponent: AuthComponent? = null

        fun init(context: Context): AuthComponent {
            if (authComponent == null) {
                authComponent = DaggerAuthComponent.create
        }
        return authComponent!!
    }
}
}
