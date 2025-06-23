package com.example.trainingapp.domain.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton


//@Module
//class AppModule(private val application: Application) {
//
//    @Provides
//    @Singleton
//    fun provideApplication(): Application = application
//
//    @Provides
//    @Singleton
//    @ApplicationContext
//    fun provideApplicationContext(): Context = application
//}
//
//@Qualifier
//@Retention(AnnotationRetention.RUNTIME)
//annotation class ApplicationContext