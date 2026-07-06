package com.example.trainings.data.mappers

import com.example.database.models.training_modules.ExerciseDbo
import com.example.database.models.training_modules.FavoriteExerciseDbo
import com.example.database.models.training_modules.PrimaryMusclesDbo
import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.local.modelsDTO.PrimaryMusclesDto
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.FullExercise
import com.example.trainings.data.response.PrimaryMuscles

object TrainingMapper {

    fun FullExercise.toFavoriteExerciseDbo(): FavoriteExerciseDbo {
        return FavoriteExerciseDbo(
            id = this.id
        )
    }

    fun List<ExerciseDto>.toExerciseList(): List<Exercise> {
        return this.map { it.toExercise() }
    }

    fun List<ExerciseDto>.toExerciseDboList(): List<ExerciseDbo> {
        return this.map { it.toExerciseDbo() }
    }

    fun List<ExerciseDbo>.toExerciseListFromDbo(): List<Exercise> {
        return this.map { it.toExercise() }
    }

    fun ExerciseDto.toExercise(): Exercise {
        return Exercise(
            id = id,
            primaryMuscles = primaryMuscles.map {
                PrimaryMuscles(
                    id = it.id,
                    name = it.name
                )
            },
            name = name,
            description = description
        )
    }

    fun ExerciseDto.toExerciseDbo(): ExerciseDbo {
        return ExerciseDbo(
            id = id,
            primaryMuscles = primaryMuscles.map {
                PrimaryMusclesDbo(
                    id = it.id,
                    name = it.name
                )
            },
            name = name,
            description = description
        )
    }

    fun ExerciseDbo.toExercise(): Exercise {
        return Exercise(
            id = id,
            primaryMuscles = primaryMuscles.map {
                PrimaryMuscles(
                    id = it.id,
                    name = it.name
                )
            },
            name = name,
            description = description
        )
    }
}
