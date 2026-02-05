package com.example.trainings.database.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.trainings.data.response.WorkoutSessionResult
import com.example.trainings.database.models.TrainingResponseDBO
import com.example.trainings.database.models.VideoResultTrainingDBO
import com.example.trainings.database.models.WorkoutSessionDBO
import com.example.trainings.database.models.WorkoutSessionResultDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDAO {


    @Query("SELECT * FROM training WHERE id = 1")
    fun observeCache(): Flow<TrainingResponseDBO?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: TrainingResponseDBO)


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