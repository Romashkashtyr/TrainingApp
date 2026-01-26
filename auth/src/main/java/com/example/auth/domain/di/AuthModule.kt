package com.example.auth.domain.di

import android.content.SharedPreferences
import com.example.auth.ui.AuthorizationPresenter
import com.example.core.exception.ExceptionCatcher
import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.repository.AuthRepository
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Module
interface AuthModule {

    @Binds
    @Singleton
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}

@Singleton
class PresenterFactory @Inject constructor(
    private val authPresenterProvider: Provider<AuthorizationPresenter>
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter = authPresenterProvider.get()
}