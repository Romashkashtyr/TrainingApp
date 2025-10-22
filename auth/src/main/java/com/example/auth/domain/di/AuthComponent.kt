package com.example.auth.domain.di

import android.content.Context
import com.google.android.datatransport.runtime.dagger.Component

@Component(modules = [AuthModule::class])
abstract class AuthComponent {

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
