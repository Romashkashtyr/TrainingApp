package com.example.splash.di.modules


import com.example.core.repository.CheckAuthRepositoryCore
import com.example.splash.ui.SplashPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Inject


@Module
class SplashModule {

    @Provides
    fun provideSplashPresenterFactory(checkAuthRepositoryCore: CheckAuthRepositoryCore): SplashPresenter {
        return SplashPresenter(checkAuthRepositoryCore)
    }

}


class SplashPresenterFactory @Inject constructor(
    private val checkAuthRepositoryCore: CheckAuthRepositoryCore
) {
    fun createSplashPresenter(): SplashPresenter {
        return SplashPresenter(checkAuthRepositoryCore)
    }
}

