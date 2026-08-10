package com.example.main.domain.usecase

import com.example.main.data.entitieModules.Steps
import com.example.main.domain.repository.StepsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStepsHistoryUseCase @Inject constructor(
    private val repository: StepsRepository
) {

     operator fun invoke(): Flow<List<Steps>> {
        return repository.observeStepsHistory()
    }
}