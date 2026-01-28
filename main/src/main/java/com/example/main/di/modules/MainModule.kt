package com.example.main.di.modules


import com.example.main.data.MainRepositoryImpl
import com.example.main.domain.MainRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface MainModule {

    @Binds
    @Singleton
    fun bindsMainRepository(impl: MainRepositoryImpl): MainRepository
}