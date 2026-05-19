package com.example.trainings.data

import android.util.Log
import com.example.core.ApiSettings
import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.mappers.TrainingMapper.toListExerciseFromDto
import com.example.trainings.data.mappers.TrainingMapper.toExerciseDbo
import com.example.trainings.data.mappers.TrainingMapper.toFavoriteExerciseDbo
import com.example.trainings.data.mappers.TrainingMapper.toListExerciseFromDbo
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.FullExercise
import com.example.trainings.data.response.NetworkService
import com.example.trainings.data.database.TrainingRoomDatabase
import com.example.trainings.data.database.models.FavoriteExerciseDbo
import com.example.trainings.domain.TrainingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingsRepositoryImpl @Inject constructor(
    private val api: NetworkService,
    private val database: TrainingRoomDatabase,
) : TrainingsRepository {

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

    override fun getExerciseImageUrl(id: String): String {
        return "${ApiSettings.BASE_URL}exercises/$id/image?format=png"
    }

    override suspend fun addFavorite(exercise: FullExercise) {
        database.trainingDao().addFavorite(
            exercise.toFavoriteExerciseDbo()
        )
    }

    override suspend fun removeFavorite(id: String) {
        database.trainingDao().removeFavorite(id)
    }

    override suspend fun isFavorite(id: String): Boolean {
        return database.trainingDao().isFavorite(id)
    }

    private suspend fun saveToDatabase(dto: ExerciseDto) {
        val exerciseDbo = dto.toExerciseDbo()
        database.trainingDao().insertExerciseCache(exerciseDbo)
    }

}
