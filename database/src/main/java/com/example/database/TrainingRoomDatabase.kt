package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.models.dao.MainDao
import com.example.database.converters.Converters
import com.example.database.models.dao.TrainingDAO
import com.example.database.models.main_modules.StepsDb
import com.example.database.models.training_modules.ExerciseDbo
import com.example.database.models.training_modules.FavoriteExerciseDbo
import com.example.database.models.training_modules.PrimaryMusclesDbo


@Database(
    entities = [
        ExerciseDbo::class,
        PrimaryMusclesDbo::class,
        FavoriteExerciseDbo::class,
        StepsDb::class
    ], version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TrainingRoomDatabase : RoomDatabase() {

    abstract fun trainingDao(): TrainingDAO

    abstract fun mainDao(): MainDao


    companion object {

        private var instance: TrainingRoomDatabase? = null

        fun initializeDb(applicationContext: Context) {
            instance = Room.databaseBuilder(
                checkNotNull(applicationContext.applicationContext),
                TrainingRoomDatabase::class.java,
                "train"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getInstanceDb(): TrainingRoomDatabase =
            instance ?: throw IllegalStateException("Unexpected database")
    }
}
