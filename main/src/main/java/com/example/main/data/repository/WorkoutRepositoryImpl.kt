package com.example.main.data.repository

import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.main.domain.repository.WorkoutRepository

class WorkoutRepositoryImpl: WorkoutRepository {
    override suspend fun getWorkout(
        type: WorkoutType,
        level: WorkoutLevel
    ): List<WorkoutExercise> {
    }
}