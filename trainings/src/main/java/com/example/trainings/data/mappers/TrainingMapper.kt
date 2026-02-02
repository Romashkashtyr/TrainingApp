package com.example.trainings.data.mappers

import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession
import com.example.trainings.data.response.WorkoutSessionResult
import com.example.trainings.domain.modelsDTO.WorkoutSessionDTO
import com.example.trainings.domain.modelsDTO.WorkoutSessionResultDTO

object TrainingMapper {

    fun WorkoutSessionDTO.toWorkoutSession(): WorkoutSession {
        return WorkoutSession(
            count = count,
            next = next,
            previous = previous,
            results = results
        )
    }

    fun WorkoutSessionResultDTO.toWorkoutSessionResult(): WorkoutSessionResult {
        return WorkoutSessionResult(
            id = id,
            day = day,
            timeStart = timeStart,
            timeEnd = timeEnd
        )


    }

}