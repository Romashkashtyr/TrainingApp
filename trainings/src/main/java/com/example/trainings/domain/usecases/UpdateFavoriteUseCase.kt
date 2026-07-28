package com.example.trainings.domain.usecases

import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
    private val repository: TrainingsRepository
) {
    suspend operator fun invoke(id: String, newIsFavorite: Boolean) {
        repository.updateFavorite(id, newIsFavorite)
    }
}