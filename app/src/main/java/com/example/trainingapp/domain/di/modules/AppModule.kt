package com.example.trainingapp.domain.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.trainingapp.Constants
import com.example.trainingapp.data.exception.ExceptionCatcher
import com.example.trainingapp.data.repository.AuthRepositoryImpl
import com.example.trainingapp.domain.repository.AuthRepository
import com.example.trainingapp.presentation.authorization.AuthorizationPresenter
import com.example.splash.ui.SplashPresenter
import com.example.trainingapp.presentation.trainings.TrainingsPresenter
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
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth,catcher,sharedPreferences)
    }
}

@Singleton
class PresenterFactory @Inject constructor(
    private val authPresenterProvider: Provider<AuthorizationPresenter>
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter = authPresenterProvider.get()
}

@Singleton
class SplashPresenterFactory @Inject constructor(
    private val authRepository: AuthRepositoryImpl
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter {
        return AuthorizationPresenter(authRepository)
    }

    fun createSplashPresenter(): SplashPresenter {
        return SplashPresenter(authRepository)
    }
}

@Singleton
class TrainingFactory @Inject constructor(
    private val trainingPresenterProvider: Provider<TrainingsPresenter>
) {
    fun createTrainingPresenter(): TrainingsPresenter = trainingPresenterProvider.get()
}