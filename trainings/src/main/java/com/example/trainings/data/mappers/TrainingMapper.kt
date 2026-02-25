package com.example.trainings.data.mappers

import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.local.modelsDTO.ExerciseInfoDto
import com.example.trainings.data.local.modelsDTO.TrainingDataDto
import com.example.trainings.data.local.modelsDTO.TrainingVideosDTO
import com.example.trainings.data.local.modelsDTO.VideoResultTrainingDTO
import com.example.trainings.data.local.modelsDTO.WorkoutSessionDTO
import com.example.trainings.data.mappers.TrainingMapper.toExercise
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.ExerciseInfo
import com.example.trainings.data.response.TrainingData
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession
import com.example.trainings.database.models.TrainingDataDbo
import com.example.trainings.database.models.TrainingVideosDBO
import com.example.trainings.database.models.VideoResultTrainingDBO
import com.example.trainings.database.models.WorkoutSessionDBO

object TrainingMapper {

    fun WorkoutSessionDTO.toWorkoutSession(): WorkoutSession {
        return WorkoutSession(
            count = count,
            next = next,
            previous = previous,
            results = results
        )
    }


    fun WorkoutSessionDTO.toWorkoutSessionDbo(): WorkoutSessionDBO {
        return WorkoutSessionDBO(
            id = id,
            count = count,
            next = next,
            previous = previous,
            results = results
        )
    }

    fun TrainingVideosDTO.toTrainingVideosDBO(): TrainingVideosDBO {
        return TrainingVideosDBO(
            count = count,
            previous = previous,
            next = next,
            result = result
        )
    }

    fun VideoResultTrainingDTO.toVideoResultTrainingDBO(): VideoResultTrainingDBO {
        return VideoResultTrainingDBO(
            id, uuid, exercise, exerciseUuid, videoUrl, isMain, duration
        )
    }

    fun VideoResultTrainingDTO.toVideoResultTraining(): VideoResultTraining {
        return VideoResultTraining(
            id, uuid, exercise, exerciseUuid, videoUrl, isMain, duration
        )
    }

    fun TrainingDataDto.toTrainingData(): TrainingData {
        return TrainingData(
            id, authorHistory, uuid, exercise, exerciseUuid, videoUrl, isMain, duration
        )
    }

    fun TrainingDataDto.toTrainingDataDbo(): TrainingDataDbo {
        return TrainingDataDbo(
            id, authorHistory, uuid, exercise, exerciseUuid, videoUrl, isMain, duration
        )
    }

    fun TrainingDataDbo.toTrainingData(): TrainingData {
        return TrainingData(
            id,authorHistory, uuid, exercise, exerciseUuid, videoUrl, isMain, duration
        )
    }

    fun TrainingData.toTrainingDataDbo(): TrainingDataDbo {
        return TrainingDataDbo(
            id, authorHistory, uuid, exercise, exerciseUuid, videoUrl, isMain, duration
        )
    }

    fun TrainingData.toTrainingDataDto(): TrainingDataDto {
        return TrainingDataDto(
            id, authorHistory, uuid, exercise, exerciseUuid, videoUrl, isMain, duration
        )
    }

    fun VideoResultTraining.toVideoResultTrainingDTO(): VideoResultTrainingDTO {
        return VideoResultTrainingDTO(
            id, uuid, exercise, exerciseUuid, videoUrl, isMain, duration
        )
    }

    fun List<ExerciseInfo>.toExerciseInfoDto(): List<ExerciseInfoDto> {
        return this.map {
            ExerciseInfoDto(
                id = it.id,
                muscles = it.muscles,
                equipment = it.equipment
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
                    muscles = it.muscles,
                    equipment = it.equipment
                )
            }
        )
    }



//    fun WorkoutSessionResultDTO.toWorkoutSessionResult(): WorkoutSessionResult {
//        return WorkoutSessionResult(
//            id = id,
//            day = day,
//            timeStart = timeStart,
//            timeEnd = timeEnd
//        )
//
//
//    }

}