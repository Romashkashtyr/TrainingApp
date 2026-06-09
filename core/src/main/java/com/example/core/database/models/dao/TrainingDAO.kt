package com.example.core.database.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.models.training_modules.ExerciseDbo
import com.example.core.database.models.training_modules.FavoriteExerciseDbo
import com.example.core.database.models.training_modules.PrimaryMusclesDbo
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM exercise WHERE id = :id LIMIT 1")
    suspend fun getExerciseById(id: String): ExerciseDbo?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(item: FavoriteExerciseDbo)

    @Query(
        """
            DELETE FROM favorite_exercises
            WHERE id = :id
        """
    )
    suspend fun removeFavorite(id: String)

    @Query(
        """
            SELECT EXISTS(
            SELECT 1 
            FROM favorite_exercises
            WHERE id=:id
            )
        """
    )
    suspend fun isFavorite(id: String): Boolean



}