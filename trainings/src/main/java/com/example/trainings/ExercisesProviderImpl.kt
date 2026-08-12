package com.example.trainings

import com.example.core.ExerciseInfo
import com.example.core.providers.ExercisesProvider
import com.example.trainings.domain.usecases.GetCombineDataAndImageTrainings
import javax.inject.Inject

class ExercisesProviderImpl @Inject constructor(
    private val getCombineDataAndImageTrainings: GetCombineDataAndImageTrainings
): ExercisesProvider {
    override suspend fun getExercises(): List<ExerciseInfo> {
        return getCombineDataAndImageTrainings().map { exercise ->
            ExerciseInfo(
                id = exercise.id,
                name = exercise.name.orEmpty(),
                description = exercise.description,
                imageUrl = exercise.imageUrl,
                muscleNames = exercise.primaryMuscles.map { it.name }
            )
        }
    }
}