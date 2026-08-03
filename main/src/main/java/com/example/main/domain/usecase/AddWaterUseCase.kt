package com.example.main.domain.usecase

import com.example.main.domain.repository.WaterRepository
import javax.inject.Inject

class AddWaterUseCase @Inject constructor(
    private val repository: WaterRepository
) {

    suspend operator fun invoke(amount: Int) {
        repository.addWater(amount)
    }
}