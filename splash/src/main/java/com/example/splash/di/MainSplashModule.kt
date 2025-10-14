package com.example.splash.di

import com.example.splash.ui.SplashPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
class MainSplashModule {

    @Provides
    @Singleton
    fun provideSplashPresenterFactory(authRepository: AuthRepository): SplashPresenter {
        return SplashPresenter(authRepository)
    }

}

@Singleton
class PresenterFactory @Inject constructor(
    private val authRepository: AuthRepositoryImpl
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter {
        return AuthorizationPresenter(authRepository)
    }

    fun createSplashPresenter(): SplashPresenter {
        return SplashPresenter(authRepository)
    }

}