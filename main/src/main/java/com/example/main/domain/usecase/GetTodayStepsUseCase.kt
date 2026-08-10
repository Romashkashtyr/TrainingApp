package com.example.main.domain.usecase

import com.example.main.domain.repository.StepsRepository
import javax.inject.Inject

class GetTodayStepsUseCase @Inject constructor(
    private val repository: StepsRepository
) {

    suspend operator fun invoke(): Int {
        return repository.getTodaySteps()
    }
}