package com.example.trainings.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trainings.database.converters.Converters
import com.example.trainings.database.models.TrainingResponseDBO
import com.example.trainings.database.models.TrainingVideosDBO
import com.example.trainings.database.models.VideoResultTrainingDBO
import com.example.trainings.database.models.WorkoutSessionDBO
import com.example.trainings.database.models.dao.TrainingDAO
import java.lang.IllegalStateException


@Database(
    entities = [
        TrainingResponseDBO::class,
        TrainingVideosDBO::class,
        VideoResultTrainingDBO::class,
        WorkoutSessionDBO::class,
    ], version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TrainingRoomDatabase : RoomDatabase() {

    abstract fun trainingDao(): TrainingDAO


    companion object {

        private var instance: TrainingRoomDatabase? = null

        fun initializeDb(applicationContext: Context) {
            instance = Room.databaseBuilder(
                checkNotNull(applicationContext.applicationContext),
                TrainingRoomDatabase::class.java,
                "train"
            ).build()
        }

        fun getInstanceDb(): TrainingRoomDatabase = instance ?: throw IllegalStateException("Unexpected database")
    }
}
