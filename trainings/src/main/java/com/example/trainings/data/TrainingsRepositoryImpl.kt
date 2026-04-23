package com.example.trainings.data

import android.util.Log
import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.mappers.TrainingMapper.toListExerciseFromDto
import com.example.trainings.data.mappers.TrainingMapper.toExerciseDbo
import com.example.trainings.data.mappers.TrainingMapper.toListExerciseFromDbo
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


//    override suspend fun loadExercises(): List<Exercise> {
//
//        Log.d("TrainingsDebug", "=== НАЧАЛО ЗАПРОСА К API ===")
//
//        val result = api.getAllExercises()
//
//        Log.d("TrainingsDebug", "Результат запроса: isSuccess = ${result.isSuccess}")
//
//        if (result.isSuccess) {
//            val response = result.getOrNull()
//
//            Log.d("TrainingsDebug", "response получен: ${response != null}")
//            if (response != null) {
//                Log.d("TrainingsDebug", "Raw data from API: ${response.toString().take(500)}")
//                val dbo = response.toListExerciseDbo()
//
//                val rowId = database.trainingDao().insertExerciseCacheWithId(dbo)
//
//                Log.d("TrainingsDebug", "Сохранено в Room, rowId = $rowId")
//
//                if (rowId != -1L) {
//                    return response.toListExercise()
//                } else {
//                    Log.e("Repo", "Не удалось сохранить данные")
//                }
//            }
//        }
//        return getCachedExercises() ?: listOf<Exercise>()
//    }

    override suspend fun loadExercises(): List<Exercise> {

        Log.d("TrainingsDebug", "=== НАЧАЛО ЗАПРОСА К API ===")

        try {
            val response = api.getAllExercises()

            if (!response.isSuccessful) {
                Log.e("TrainingsDebug", "HTTP error: ${response.code()}")
                return getCachedExercises() ?: emptyList()
            }

            val body = response.body()
            if (body.isNullOrEmpty()) {
                Log.e("TrainingsDebug", "Пустой ответ от API")
                return getCachedExercises() ?: emptyList()
            }

            Log.d("TrainingsDebug", "Получено элементов: ${body.size}")

            val dboList = body.map { it.toExerciseDbo() }


            val ids = database.trainingDao().insertExerciseCacheWithId(dboList)

            Log.d("TrainingsDebug", "Сохранено в Room, ids count = ${ids.size}")


            return body.map { it.toListExerciseFromDto() }

        } catch (e: Exception) {
            Log.e("TrainingsDebug", "Ошибка: ${e.message}", e)

            return getCachedExercises() ?: emptyList()
        }
    }

    override suspend fun getCachedExercises(): List<Exercise>? {
        val cache = database.trainingDao().getCache() ?: return null
        return cache.toListExerciseFromDbo()
    }

    private suspend fun saveToDatabase(dto: ExerciseDto) {
        val exerciseDbo = dto.toExerciseDbo()
        database.trainingDao().insertExerciseCache(exerciseDbo)
    }

}
