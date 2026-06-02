package com.example.main.di.modules


import android.content.Context
import com.example.core.data.datastore.StepsDataStore
import com.example.main.data.MainRepositoryImpl
import com.example.main.data.repository.StepsRepositoryImpl
import com.example.main.domain.MainRepository
import com.example.main.domain.StepsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface MainModule {

    @Binds
    @Singleton
    fun bindsMainRepository(impl: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    fun bindStepsRepository(impl: StepsRepositoryImpl): StepsRepository

}