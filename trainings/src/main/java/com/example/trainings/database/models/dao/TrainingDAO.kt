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
    suspend fun insertExerciseCache(cache: ExerciseDbo): Exercise

// probably temporary
//    @Query("SELECT * FROM training WHERE id = 1")
//    fun observeCache(): Flow<TrainingResponseDBO?>
//
//    @Query("SELECT * FROM training_data")
//    fun observeAll(): Flow<List<TrainingDataDbo>>
//
//
//    @Query("SELECT * FROM training_data")
//    fun getAll(): List<TrainingDataDbo?>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCache(cache: TrainingResponseDBO)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertDataCache(cache: List<TrainingDataDbo>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCacheVideo(cache: List<VideoResultTrainingDBO>)
//
//    @Query("SELECT * FROM video_results")
//    fun observeVideos(): Flow<List<VideoResultTrainingDBO>>
//
//
//    @Query("SELECT * FROM workout_sessions")
//    fun observeWorkoutSession(): Flow<List<WorkoutSessionDBO>>
// probably temporary

//    @Query("SELECT * FROM workout_sessions")
//    fun observeWorkoutSession(): Flow<WorkoutSessionResult>
//
//    @Query("SELECT * FROM video_results")
//    fun observeVideos(): Flow<List<VideoResultTrainingDBO>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWorkoutSession(model: WorkoutSessionDBO)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWorkoutResults(models: List<WorkoutSessionResultDBO>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertVideoResults(models: List<VideoResultTrainingDBO>)
}