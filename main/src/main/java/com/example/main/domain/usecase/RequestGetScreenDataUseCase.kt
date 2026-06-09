package com.example.main.domain.usecase

import com.example.core.structures.Status
import com.example.main.domain.repository.MainRepository
import javax.inject.Inject

class RequestGetScreenDataUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): Status<Int> {
        return mainRepository.getWaterAmount()
    }
}