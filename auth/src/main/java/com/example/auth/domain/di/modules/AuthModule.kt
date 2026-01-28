package com.example.auth.domain.di.modules

import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.repository.AuthRepository
import com.example.auth.ui.AuthorizationPresenter
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Module
interface AuthModule {

    @Binds
    @Singleton
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}


class PresenterFactory @Inject constructor(
    private val authPresenterProvider: Provider<AuthorizationPresenter>
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter = authPresenterProvider.get()
}