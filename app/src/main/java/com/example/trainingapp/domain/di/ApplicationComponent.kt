package com.example.trainingapp.domain.di

import com.example.trainingapp.domain.di.modules.AppModule
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


//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun context(context: Context): Builder
//        fun build(): ApplicationComponent
//    }


}