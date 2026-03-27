package com.example.trainings.data

import android.util.Log
import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.mappers.TrainingMapper.toExercise
import com.example.trainings.data.mappers.TrainingMapper.toExerciseDbo
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.NetworkService
import com.example.trainings.database.TrainingRoomDatabase
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

        if (result.isSuccess) {
            val response = result.getOrNull()

            if (response != null) {
                val dbo = response.toExerciseDbo()

                val success = database.trainingDao().insertExerciseCache(dbo)

                if (success) {
                    return response.toExercise()
                } else {
                    Log.e("Repo", "Не удалось сохранить данные")
                }
            }
        }
        return getCachedExercises() ?: Exercise(0, null, null, emptyList())
    }

    override suspend fun getCachedExercises(): Exercise? {
        val cache = database.trainingDao().getCache() ?: return null

        return cache.toExercise()
    }

    private suspend fun saveToDatabase(dto: ExerciseDto) {
        val exerciseDbo = dto.toExerciseDbo()
        database.trainingDao().insertExerciseCache(exerciseDbo)
    }

}
