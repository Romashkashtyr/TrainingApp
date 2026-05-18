package com.example.trainings.domain.usecases

import com.example.trainings.data.response.Exercise
import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject

class GetTrainingDataUseCase @Inject constructor(
    private val repository: TrainingsRepository
){

    suspend operator fun invoke(): List<Exercise> {
        return repository.loadExercises()
    }
}