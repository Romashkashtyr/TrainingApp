package com.example.trainingapp.domain.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.core.Constants
import com.example.core.exception.ExceptionCatcher
import com.example.trainingapp.data.repository.AuthRepositoryImpl
import com.example.auth.AuthorizationPresenter
import com.example.auth.repository.AuthRepository
import com.example.splash.ui.SplashPresenter
import com.example.trainings.TrainingsPresenter
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.AUTH_PREFS, Context.MODE_PRIVATE)
    }

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
    private val authPresenterProvider: Provider<com.example.auth.AuthorizationPresenter>
) {
    fun createAuthorizationPresenter(): com.example.auth.AuthorizationPresenter = authPresenterProvider.get()
}

@Singleton
class SplashPresenterFactory @Inject constructor(
    private val authRepository: AuthRepositoryImpl
) {
    fun createAuthorizationPresenter(): com.example.auth.AuthorizationPresenter {
        return com.example.auth.AuthorizationPresenter(authRepository)
    }

    fun createSplashPresenter(): SplashPresenter {
        return SplashPresenter(authRepository)
    }
}

@Singleton
class TrainingFactory @Inject constructor(
    private val trainingPresenterProvider: Provider<com.example.trainings.TrainingsPresenter>
) {
    fun createTrainingPresenter(): com.example.trainings.TrainingsPresenter = trainingPresenterProvider.get()
}