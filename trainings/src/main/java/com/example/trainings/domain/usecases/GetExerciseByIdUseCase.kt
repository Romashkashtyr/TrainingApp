package com.example.trainings.domain.usecases

import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.FullExercise
import com.example.trainings.data.response.FullExercise.Companion.toFullExercise
import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject

class GetExerciseByIdUseCase @Inject constructor(
    private val repository: TrainingsRepository
){

    suspend operator fun invoke(id: String): FullExercise {
        val exercise = repository.getExerciseById(id)

        return exercise.toFullExercise(
            image = repository.getExerciseImageUrl(id)
        )
    }
}