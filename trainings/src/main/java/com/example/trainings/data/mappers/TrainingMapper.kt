package com.example.trainings.data.mappers

import com.example.trainings.data.local.modelsDTO.EquipmentDto
import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.local.modelsDTO.ExerciseInfoDto
import com.example.trainings.data.local.modelsDTO.MusclesDto
import com.example.trainings.data.local.modelsDTO.TrainingDataDto
import com.example.trainings.data.mappers.TrainingMapper.toExercise
import com.example.trainings.data.mappers.TrainingMapper.toExerciseDbo
import com.example.trainings.data.response.Equipment
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.ExerciseInfo
import com.example.trainings.data.response.Muscles
import com.example.trainings.data.response.TrainingData
import com.example.trainings.database.models.EquipmentDbo
import com.example.trainings.database.models.ExerciseDbo
import com.example.trainings.database.models.ExerciseInfoDbo
import com.example.trainings.database.models.MusclesDbo

object TrainingMapper {


    fun List<ExerciseInfo>.toExerciseInfoDto(): List<ExerciseInfoDto> {
        return this.map {
            ExerciseInfoDto(
                id = it.id,
                muscles = it.muscles.map { muscle ->
                    MusclesDto(
                        id = it.id,
                        name = muscle.name,
                        imageUrlMain = muscle.imageUrlMain
                    )
                },
                equipment = it.equipment.map { equipment ->
                    EquipmentDto(
                        id = equipment.id,
                        name = equipment.name
                    )
                }
            )
        }
    }

    fun List<ExerciseInfo>.toExerciseInfo(): List<ExerciseInfo> {
        return this.map {
            ExerciseInfo(
                id = it.id,
                muscles = it.muscles,
                equipment = it.equipment
            )
        }
    }

    fun ExerciseDto.toExercise(): Exercise {
        return Exercise(
            count = count,
            next = next,
            previous = previous,
            results = results.map {
                ExerciseInfo(
                    id = it.id,
                    muscles = it.muscles.map { muscle ->
                        Muscles(
                            name = muscle.name,
                            imageUrlMain = muscle.imageUrlMain
                        )
                    },
                    equipment = it.equipment.map { equip ->
                        Equipment(
                            id = equip.id,
                            name = equip.name
                        )
                    }
                )
            }
        )
    }


    fun ExerciseDto.toExerciseDbo(): ExerciseDbo {
        return ExerciseDbo(
            count = count,
            next = next,
            previous = previous,
            results = results.map {exerciseInfo ->
                ExerciseInfoDbo(
                    id = exerciseInfo.id,
                    muscles = exerciseInfo.muscles.map {
                        MusclesDbo(
                            id = it.id,
                            name = it.name,
                            imageUrlMain = it.imageUrlMain
                        )
                    },
                    equipment = exerciseInfo.equipment.map {
                        EquipmentDbo(
                            id = it.id,
                            name = it.name
                        )
                    }
                )
            }
        )
    }


    fun ExerciseDbo.toExercise(): Exercise? {
        return results?.map { exerciseInfo ->
            ExerciseInfo(
                id = exerciseInfo.id,
                muscles = exerciseInfo.muscles.map {
                    Muscles(
                        name = it.name,
                        imageUrlMain = it.imageUrlMain
                    )
                },
                equipment = exerciseInfo.equipment.map {
                    Equipment(
                        id = it.id,
                        name = it.name
                    )
                }
            )
        }?.let {
            Exercise(
                count = count ?: 0,
                next = next,
                previous = previous,
                results = it
            )
        }
    }


}