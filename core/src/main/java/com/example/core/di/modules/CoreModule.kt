package com.example.core.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.core.Constants
import com.example.core.data.datastore.StepsDataStore
import com.example.core.data.repository.CheckAuthRepositoryCoreImpl
import com.example.core.di.CoreComponent
import com.example.core.repository.CheckAuthRepositoryCore
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface CoreModule {

    @Binds
    fun bindAuthRepositoryCore(impl: CheckAuthRepositoryCoreImpl): CheckAuthRepositoryCore

    companion object {

        private val Context.dataStore by preferencesDataStore(
            name = "steps_prefs"
        )

        @Provides
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }

        @Provides
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(Constants.AUTH_PREFS, Context.MODE_PRIVATE)
        }

        @Provides
        fun provideDataStore(context: Context): DataStore<Preferences> {
            return context.dataStore
        }

        @Provides
        @Singleton
        fun provideStepsDataStore(context: Context): StepsDataStore {
            return StepsDataStore(context.applicationContext)
        }


//        @Provides
//        @Singleton
//        fun provideContext(): Context = co
    }

}
