package com.example.trainings.data.mappers

import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.local.modelsDTO.PrimaryMusclesDto
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.ExerciseInterface
import com.example.trainings.data.response.FullExercise
import com.example.trainings.data.response.PrimaryMuscles
import com.example.trainings.data.database.models.ExerciseDbo
import com.example.trainings.data.database.models.FavoriteExerciseDbo
import com.example.trainings.data.database.models.PrimaryMusclesDbo

object TrainingMapper {


    fun List<Exercise>.toExerciseDto(): List<ExerciseDto> {
        return this.map {
            it.name?.let { muscle ->
                it.description?.let { description ->
                    ExerciseDto(
                        id = it.id,
                        primaryMuscles = it.primaryMuscles.map {
                            PrimaryMusclesDto(
                                id = it.id,
                                name = it.name
                            )
                        },
                        name = muscle,
                        description = description
                    )
                }
            }!!
        }
    }

    fun FullExercise.toFavoriteExerciseDbo(): FavoriteExerciseDbo {
        return FavoriteExerciseDbo(
            id = this.id,
            name = this.name,
            description = this.description ?: "",
            imageUrl = this.imageUrl
        )
    }


    fun List<ExerciseDto>.toListExerciseFromDto(): List<Exercise> {
        return this.map {
            Exercise(
                id = it.id,
                primaryMuscles = it.primaryMuscles.map {
                    PrimaryMuscles(
                        id = it.id,
                        name = it.name
                    )
                },
                name = it.name,
                description = it.description
            )
        }
    }


    fun List<ExerciseDto>.toListExerciseDbo(): List<ExerciseDbo> {
        return this.map {
            ExerciseDbo(
                id = it.id,
                primaryMuscles = it.primaryMuscles.map {
                    PrimaryMusclesDbo(
                        id = it.id,
                        name = it.name
                    )
                },
                name = it.name,
                description = it.description
            )
        }
    }


    fun List<ExerciseDbo>.toListExerciseFromDbo(): List<Exercise> {
        return this.map {
            Exercise(
                id = it.id,
                primaryMuscles = it.primaryMuscles.map {
                    PrimaryMuscles(
                        id = it.id,
                        name = it.name
                    )
                },
                name = it.name,
                description = it.description
            )
        }
    }


    fun ExerciseDto.toListExerciseFromDto(): Exercise {
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


    fun ExerciseDbo.toListExerciseFromDto(): Exercise? {
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