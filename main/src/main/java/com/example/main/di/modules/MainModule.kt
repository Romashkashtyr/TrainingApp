package com.example.main.di.modules



import com.example.database.TrainingRoomDatabase
import com.example.main.data.repository.MainRepositoryImpl
import com.example.main.data.repository.StepsRepositoryImpl
import com.example.main.data.repository.WaterRepositoryImpl
import com.example.main.domain.repository.MainRepository
import com.example.main.domain.repository.StepsRepository
import com.example.main.domain.repository.WaterRepository
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

    @Binds
    @Singleton
    fun bindWaterRepository(impl: WaterRepositoryImpl): WaterRepository

    companion object {
        @Provides
        @Singleton
        fun provideTrainingRoomDatabase(): TrainingRoomDatabase {
            return TrainingRoomDatabase.getInstanceDb()
        }
    }

}