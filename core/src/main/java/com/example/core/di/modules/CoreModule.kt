package com.example.core.di.modules

import com.example.core.data.AuthRepositoryCoreImpl
import com.example.core.repository.AuthRepositoryCore
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface CoreModule {

    @Binds
    fun bindAuthRepositoryCore(impl: AuthRepositoryCoreImpl): AuthRepositoryCore
}