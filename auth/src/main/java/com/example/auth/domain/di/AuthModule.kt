package com.example.auth.domain.di

import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.repository.AuthRepository
import com.example.auth.ui.AuthorizationPresenter
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Module
interface AuthModule {

    @Binds
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}


class PresenterFactory @Inject constructor(
    private val authPresenterProvider: Provider<AuthorizationPresenter>
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter = authPresenterProvider.get()
}