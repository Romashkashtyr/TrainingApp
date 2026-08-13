package com.example.main.domain.usecase

import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.main.domain.repository.WorkoutRepository
import javax.inject.Inject

class GetWorkoutUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {

    suspend operator fun invoke(
        type: WorkoutType,
        level: WorkoutLevel
    ): List<WorkoutExercise> {
        return repository.getWorkout(type, level)
    }
}