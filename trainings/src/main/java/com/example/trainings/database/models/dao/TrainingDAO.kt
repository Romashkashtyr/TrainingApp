package com.example.trainings.database.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trainings.database.models.ExerciseDbo
import com.example.trainings.database.models.PrimaryMusclesDbo

@Dao
interface TrainingDAO {


    @Query("SELECT * FROM exercise")
    suspend fun getCache(): List<ExerciseDbo>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseCache(cache: ExerciseDbo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseCacheWithId(cache: List<ExerciseDbo>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrimaryMuscles(list: List<PrimaryMusclesDbo>)


}