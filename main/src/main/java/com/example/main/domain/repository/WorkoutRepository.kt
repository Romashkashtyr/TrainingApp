package com.example.main.domain.repository

import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType

interface WorkoutRepository {

     suspend fun getWorkout(
         type: WorkoutType,
         level: WorkoutLevel
     ): List<WorkoutExercise>
}