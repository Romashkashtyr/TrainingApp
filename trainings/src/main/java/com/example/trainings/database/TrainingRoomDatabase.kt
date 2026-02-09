package com.example.trainings.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trainings.database.models.TrainingResponseDBO
import com.example.trainings.database.models.dao.TrainingDAO


class TrainingDatabase(private val database: TrainingRoomDatabase) {
    val dao: TrainingDAO
        get() = database.trainingDao()
}

@Database(entities = [TrainingResponseDBO::class], version = 1)
abstract class TrainingRoomDatabase: RoomDatabase(){
    abstract fun trainingDao(): TrainingDAO
}


fun TrainingDatabase(applicationContext: Context): TrainingDatabase {
    val trainingRoomDatabase = Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        TrainingRoomDatabase::class.java,
        "train"
    ).build()

    return TrainingDatabase(trainingRoomDatabase)
}