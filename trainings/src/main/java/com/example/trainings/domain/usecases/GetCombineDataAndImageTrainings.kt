package com.example.trainings.domain.usecases


import com.example.trainings.data.response.FullExercise
import com.example.trainings.data.response.FullExercise.Companion.toFullExercise
import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject

class GetCombineDataAndImageTrainings @Inject constructor(
    private val repository: TrainingsRepository
) {

    suspend operator fun invoke(): List<FullExercise> {
        val exercises = repository.loadExercises()
        return exercises.map { it.toFullExercise(image = repository.getExerciseImageUrl(id = it.id), isFavorite = repository.isFavorite(it.id)) }
    }
}