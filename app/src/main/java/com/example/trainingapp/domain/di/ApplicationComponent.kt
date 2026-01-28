package com.example.trainingapp.domain.di

import android.content.Context
import com.example.auth.ui.AuthorizationActivity
import com.example.splash.ui.SplashActivity
import com.example.trainingapp.domain.di.modules.AppModule
import com.example.trainings.ui.TrainingsListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface ApplicationComponent {

//    fun inject(activity: TrainingsListActivity)
//    fun inject(activity: AuthorizationActivity)
//    fun inject(activity: SplashActivity)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): ApplicationComponent
    }


}