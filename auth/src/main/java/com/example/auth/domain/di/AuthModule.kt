package com.example.auth.domain.di

import android.content.SharedPreferences
import com.example.auth.ui.AuthorizationPresenter
import com.example.core.exception.ExceptionCatcher
import com.example.auth.data.AuthRepositoryImpl
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Module
abstract class AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        catcher: ExceptionCatcher,
        sharedPreferences: SharedPreferences
    ): AuthRepositoryImpl {
        return AuthRepositoryImpl(firebaseAuth,catcher,sharedPreferences)
    }
}

@Singleton
class PresenterFactory @Inject constructor(
    private val authPresenterProvider: Provider<AuthorizationPresenter>
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter = authPresenterProvider.get()
}