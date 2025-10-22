package com.example.auth.domain.di

import com.google.android.datatransport.runtime.dagger.Component

@Component(modules = [AuthModule::class])
abstract class AuthComponent {

    companion object {

        private var authComponent: AuthComponent? = null

        fun init(): AuthComponent {
            if (authComponent == null) {
                authComponent = DaggerAuthComponent.create
        }
        return authComponent!!
    }
}
}
