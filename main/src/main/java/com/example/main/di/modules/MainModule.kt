package com.example.main.di.modules


import com.example.main.data.MainRepositoryImpl
import com.example.main.domain.MainRepository
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import javax.inject.Singleton

@Module
interface MainModule {

    @Binds
    fun bindsMainRepository(impl: MainRepositoryImpl): MainRepository
}