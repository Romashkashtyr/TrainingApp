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

        Log.d("TrainingsDebug", "=== НАЧАЛО ЗАПРОСА К API ===")

        val result = api.getAllExercises()

        Log.d("TrainingsDebug", "Результат запроса: isSuccess = ${result.isSuccess}")

        if (result.isSuccess) {
            val response = result.getOrNull()

            Log.d("TrainingsDebug", "response получен: ${response != null}")
            if (response != null) {
                Log.d("TrainingsDebug", "Raw data from API: ${response.toString().take(500)}")
                val dbo = response.toExerciseDbo()

                val rowId = database.trainingDao().insertExerciseCacheWithId(dbo)

                Log.d("TrainingsDebug", "Сохранено в Room, rowId = $rowId")

                if (rowId != -1L) {
                    return response.toExercise()
                } else {
                    Log.e("Repo", "Не удалось сохранить данные")
                }
            }
        }
        return getCachedExercises() ?: Exercise(0, emptyList(), null, null)
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
