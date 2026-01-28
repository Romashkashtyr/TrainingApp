package com.example.trainingapp.domain.di.modules

import com.example.auth.ui.AuthorizationPresenter
import com.example.core.repository.AuthRepositoryCore
import com.example.splash.ui.SplashPresenter
import com.example.trainings.ui.TrainingsPresenter
import dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Module
class AppModule {



}

@Singleton
class PresenterFactory @Inject constructor(
    private val authPresenterProvider: Provider<AuthorizationPresenter>
) {
    fun createAuthorizationPresenter(): AuthorizationPresenter = authPresenterProvider.get()
}

@Singleton
class SplashPresenterFactory @Inject constructor(
    private val authRepositoryCore: AuthRepositoryCore
) {

    fun createSplashPresenter(): SplashPresenter {
        return SplashPresenter(authRepositoryCore)
    }
}

@Singleton
class TrainingFactory @Inject constructor(
    private val trainingPresenterProvider: Provider<TrainingsPresenter>
) {
    fun createTrainingPresenter(): TrainingsPresenter = trainingPresenterProvider.get()
}