package com.example.core.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.core.Constants
import com.example.core.data.repository.CheckAuthRepositoryCoreImpl
import com.example.core.repository.CheckAuthRepositoryCore
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface CoreModule {

    @Binds
    fun bindAuthRepositoryCore(impl: CheckAuthRepositoryCoreImpl): CheckAuthRepositoryCore

    companion object {

        @Provides
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }

        @Provides
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(Constants.AUTH_PREFS, Context.MODE_PRIVATE)
        }
    }

}
