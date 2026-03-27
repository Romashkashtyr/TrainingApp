package com.example.trainings.database.models.dao

import androidx.annotation.LongDef
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.trainings.data.response.Exercise
import com.example.trainings.database.models.EquipmentDbo
import com.example.trainings.database.models.ExerciseDbo
import com.example.trainings.database.models.ExerciseInfoDbo
import com.example.trainings.database.models.MusclesDbo

@Dao
interface TrainingDAO {


    @Query("SELECT * FROM exercise_info")
    suspend fun getCache(): ExerciseDbo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseCache(cache: ExerciseDbo): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseCacheWithId(cache: ExerciseDbo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseInfoList(list: List<ExerciseInfoDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMuscles(list: List<MusclesDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEquipment(list: List<EquipmentDbo>)


}