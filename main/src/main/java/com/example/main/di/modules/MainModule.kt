package com.example.main.di.modules


import android.content.Context
import com.example.core.data.datastore.StepsDataStore
import com.example.main.data.MainRepositoryImpl
import com.example.main.domain.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface MainModule {

    @Binds
    @Singleton
    fun bindsMainRepository(impl: MainRepositoryImpl): MainRepository


}