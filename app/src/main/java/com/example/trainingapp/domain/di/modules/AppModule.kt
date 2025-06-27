package com.example.trainingapp.domain.di.modules

import android.app.Application
import android.content.Context
import com.example.trainingapp.data.exception.ExceptionCatcher
import com.example.trainingapp.presentation.signin.AuthorizationPresenter
import com.example.trainingapp.presentation.trainings.TrainingsPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {




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