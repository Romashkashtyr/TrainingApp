package com.example.main.di.modules


import com.example.main.data.repository.MainRepositoryImpl
import com.example.main.data.repository.StepsRepositoryImpl
import com.example.main.domain.repository.MainRepository
import com.example.main.domain.repository.StepsRepository
import dagger.Binds
import dagger.Module
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