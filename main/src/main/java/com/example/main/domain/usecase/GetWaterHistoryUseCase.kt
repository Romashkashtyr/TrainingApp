package com.example.main.domain.usecase

import com.example.main.data.entitieModules.Water
import com.example.main.domain.repository.WaterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWaterHistoryUseCase @Inject constructor(
    private val repository: WaterRepository
) {

    operator fun invoke(): Flow<List<Water>> {
        return repository.observeHistory()
    }
}