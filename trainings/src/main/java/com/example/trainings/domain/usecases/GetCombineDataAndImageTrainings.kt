package com.example.trainings.domain.usecases


import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject

class GetCombineDataAndImageTrainings @Inject constructor(
    private val repository: TrainingsRepository
) {

    suspend operator fun invoke(): List<FullExercise> {
        val exercises = repository.loadExercises()

        return exercises.map { exercise ->
            FullExercise(
                id = exercise.id,
                name = exercise.name,
                description = exercise.description,
                primaryMuscles = exercise.primaryMuscles,
                imageUrl = repository.getExerciseImageUrl(exercise.id)
            )
        }
    }
}