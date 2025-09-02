package com.example.trainingapp.domain.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.trainingapp.Constants
import com.example.trainingapp.data.exception.ExceptionCatcher
import com.example.trainingapp.presentation.signin.AuthorizationPresenter
import com.example.trainingapp.presentation.trainings.TrainingsPresenter
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Qualifier
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
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences(Constants.AUTH_PREFS, Context.MODE_PRIVATE)
    }
}

@Singleton
class PresenterFactory @Inject constructor(
    private val authPresenterProvider: Provider<AuthorizationPresenter>
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter = authPresenterProvider.get()
}

@Singleton
class TrainingFactory @Inject constructor(
    private val trainingPresenterProvider: Provider<TrainingsPresenter>
) {
    fun createTrainingPresenter(): TrainingsPresenter = trainingPresenterProvider.get()
}