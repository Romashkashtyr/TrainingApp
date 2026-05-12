package com.example.trainings.ui.usecases

import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.ExerciseUi
import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject

class GetCombineDataAndImageTrainings @Inject constructor(
    private val repository: TrainingsRepository
) {

    suspend operator fun invoke(): List<ExerciseUi> {
        val exercises = repository.loadExercises()

        return exercises.map { exercise ->
            ExerciseUi(
                id = exercise.id,
                name = exercise.name,
                description = exercise.description,
                primaryMuscles = exercise.primaryMuscles,
                imageUrl = repository.getExerciseImageUrl(exercise.id)
            )
        }
    }
}