package com.example.auth.domain.di


import com.example.auth.repository.AuthRepository
import com.example.core.di.CoreComponent
import com.google.android.datatransport.runtime.dagger.Component


@Component(modules = [AuthModule::class], dependencies = [CoreComponent::class])
interface  AuthComponent {

    fun authRepository(): AuthRepository

    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): AuthComponent

    }

    companion object {

        private var instance: AuthComponent? = null

        fun init(coreComponent: CoreComponent): AuthComponent {
            if (instance == null) {
                instance = DaggerAuthComponent.builder().coreComponent(coreComponent).build()
        }
        return instance!!
    }
}
}
