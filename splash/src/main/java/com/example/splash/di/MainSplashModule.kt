package com.example.splash.di


import com.example.core.repository.AuthRepository
import com.example.splash.ui.SplashPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
class MainSplashModule {

    // TODO()
//    @Provides
//    @Singleton
//    fun provideSplashPresenterFactory(authRepository: AuthRepository): SplashPresenter {
//        return SplashPresenter(authRepository)
//    }

}

@Singleton
class SplashPresenterFactory @Inject constructor(
    private val authRepository: AuthRepository
) {
    fun createSplashPresenter(): SplashPresenter {
        return SplashPresenter(authRepository)
    }
}

