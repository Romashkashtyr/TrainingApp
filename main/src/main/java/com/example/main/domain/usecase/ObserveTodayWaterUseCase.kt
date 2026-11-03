package com.example.main.domain.usecase

import com.example.main.domain.repository.WaterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTodayWaterUseCase @Inject constructor(
    private val repository: WaterRepository
) {

    operator fun invoke(): Flow<Int> {
        return repository.observeTodayWater()
    }
}