package com.example.main.domain.usecase

import com.example.main.data.entitieModules.WorkoutHistory
import com.example.main.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkoutHistoryUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {

     operator fun invoke(): Flow<List<WorkoutHistory>> {
        return repository.observeHistory()
    }
}