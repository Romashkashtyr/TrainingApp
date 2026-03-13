package com.example.trainings.data

import com.example.trainings.data.mappers.TrainingMapper.toExerciseDbo
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.NetworkService
import com.example.trainings.database.TrainingRoomDatabase
import com.example.trainings.database.models.ExerciseDbo
import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingsRepositoryImpl @Inject constructor(
    private val api: NetworkService,
    private val database: TrainingRoomDatabase
) : TrainingsRepository {


    override suspend fun loadExercises(): Exercise {
        val result = api.getExerciseInfo()

        return if (result.isSuccess) {
            val response = result.getOrNull()?.toExerciseDbo()

            database.trainingDao().insertExerciseCache(
                ExerciseDbo(
                    count = response?.count,
                    next = response?.next,
                    previous = response?.previous,
                    results = response?.results
                )
            )
        } else {
            getCachedExercises()
        }
    }

    override suspend fun getCachedExercises(): Exercise {
        TODO("Not yet implemented")
    }


}