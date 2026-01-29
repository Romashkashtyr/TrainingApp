package com.example.auth.domain.di


import com.example.auth.domain.di.modules.AuthModule
import com.example.auth.repository.AuthRepository
import com.example.auth.ui.AuthorizationActivity
import com.example.core.di.CoreComponent
import dagger.Component
import java.lang.IllegalStateException
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthModule::class], dependencies = [CoreComponent::class])
interface AuthComponent {

    fun inject(activity: AuthorizationActivity)

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

        fun getAuthInstance(): AuthComponent {
            return if (instance != null) instance!! else throw IllegalStateException()
        }
    }
}
