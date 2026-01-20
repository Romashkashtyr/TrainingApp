package com.example.trainingapp.domain.di

import android.content.Context
import com.example.auth.ui.AuthorizationActivity
import com.example.splash.ui.SplashActivity
import com.example.trainingapp.domain.di.modules.AppModule
import com.example.auth.domain.di.AuthBindModule
import com.example.core.di.ExceptionModule
import com.example.main.di.MainModule
import com.example.splash.di.SplashComponent
import com.example.trainingapp.domain.di.modules.SubcomponentsModule
import com.example.trainings.ui.TrainingsListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ExceptionModule::class,
        AppModule::class,
        AuthBindModule::class,
        MainModule::class,
        SubcomponentsModule::class
    ]
)
interface ApplicationComponent {


    fun splashComponentFactory(): SplashComponent.SplashFactorySub

    fun inject(activity: TrainingsListActivity)
    fun inject(activity: AuthorizationActivity)
    // fun inject(activity: SplashActivity)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): ApplicationComponent
    }


}