package com.example.trainings.domain.usecases

import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: TrainingsRepository
) {

    suspend operator fun invoke(
        exercise: FullExercise
    ): Boolean {
        val isFavorite = repository.isFavorite(exercise.id)

        if (isFavorite) {
            repository.removeFavorite(exercise.id)

            return false
        }

        repository.addFavorite(exercise)

        return true
    }
}