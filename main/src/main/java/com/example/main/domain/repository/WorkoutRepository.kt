package com.example.main.domain.repository

import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutHistory
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

     suspend fun getWorkout(
         type: WorkoutType,
         level: WorkoutLevel
     ): List<WorkoutExercise>

    fun observeHistory(): Flow<List<WorkoutHistory>>
}