package com.example.trainings.di.modules

import com.example.trainings.data.database.TrainingRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class TrainingDatabaseModule {


    @Provides
    @Singleton
    fun provideTrainingRoomDatabase(): TrainingRoomDatabase {
        return TrainingRoomDatabase.getInstanceDb()
    }


}