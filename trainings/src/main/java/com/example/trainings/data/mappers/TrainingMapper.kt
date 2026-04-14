package com.example.trainings.data.mappers

import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.local.modelsDTO.MusclesDto
import com.example.trainings.data.local.modelsDTO.PrimaryMusclesDto
import com.example.trainings.data.mappers.TrainingMapper.toExercise
import com.example.trainings.data.mappers.TrainingMapper.toExerciseDbo
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.PrimaryMuscles
import com.example.trainings.database.models.ExerciseDbo
import com.example.trainings.database.models.PrimaryMusclesDbo

object TrainingMapper {


    fun List<Exercise>.toExerciseDto(): List<ExerciseDto> {
        return this.map {
            it.musclesName?.let { muscle ->
                it.description?.let { description ->
                    ExerciseDto(
                        id = it.id,
                        primaryMuscles = it.primaryMuscles.map {
                            PrimaryMusclesDto(
                                id = it.id,
                                name = it.name
                            )
                        },
                        musclesName = muscle,
                        description = description
                    )
                }
            }!!
        }
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
            musclesName = musclesName,
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
            musclesName = musclesName,
            description = description
        )
    }


    fun ExerciseDbo.toExercise(): Exercise? {
        return Exercise(
            id = id,
            primaryMuscles = primaryMuscles.map {
                PrimaryMuscles(
                    id = it.id,
                    name = it.name
                )
            },
            musclesName = musclesName,
            description = description
        )
    }
}