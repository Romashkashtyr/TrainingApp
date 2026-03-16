package com.example.trainings.database.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trainings.data.response.Exercise
import com.example.trainings.database.models.ExerciseDbo
import com.example.trainings.database.models.ExerciseInfoDbo

@Dao
interface TrainingDAO {


    @Query("SELECT * FROM exercise_info")
    suspend fun getCache(): ExerciseDbo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseCache(cache: ExerciseDbo): Boolean

}