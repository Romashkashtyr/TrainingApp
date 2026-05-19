package com.example.trainings.data.database.models.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trainings.data.database.models.ExerciseDbo
import com.example.trainings.data.database.models.FavoriteExerciseDbo
import com.example.trainings.data.database.models.PrimaryMusclesDbo

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