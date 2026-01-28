package com.example.splash.di.modules


import com.example.core.repository.AuthRepositoryCore
import com.example.splash.ui.SplashPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Inject


@Module
class MainSplashModule {

    // TODO()
    @Provides
    fun provideSplashPresenterFactory(authRepositoryCore: AuthRepositoryCore): SplashPresenter {
        return SplashPresenter(authRepositoryCore)
    }

}


class SplashPresenterFactory @Inject constructor(
    private val authRepositoryCore: AuthRepositoryCore
) {
    fun createSplashPresenter(): SplashPresenter {
        return SplashPresenter(authRepositoryCore)
    }
}

