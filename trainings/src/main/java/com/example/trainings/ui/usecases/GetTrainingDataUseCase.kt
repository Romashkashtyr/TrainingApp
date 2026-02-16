package com.example.trainings.ui.usecases

import com.example.trainings.data.RequestResult
import com.example.trainings.domain.TrainingsRepository
import com.example.trainings.ui.TrainingUI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrainingDataUseCase @Inject constructor(
    private val repository: TrainingsRepository
){

    operator fun invoke(query: String): Flow<RequestResult<List<TrainingUI> {
        return repository.
    }
}