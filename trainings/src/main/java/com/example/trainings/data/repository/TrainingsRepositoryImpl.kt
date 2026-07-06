package com.example.trainings.data.repository

import android.util.Log
import com.example.core.ApiSettings
import com.example.database.TrainingRoomDatabase
import com.example.trainings.data.mappers.TrainingMapper.toExercise
import com.example.trainings.data.mappers.TrainingMapper.toExerciseDbo
import com.example.trainings.data.mappers.TrainingMapper.toExerciseDboList
import com.example.trainings.data.mappers.TrainingMapper.toExerciseList
import com.example.trainings.data.mappers.TrainingMapper.toExerciseListFromDbo
import com.example.trainings.data.mappers.TrainingMapper.toFavoriteExerciseDbo
import com.example.trainings.data.response.Exercise
import com.example.trainings.data.response.FullExercise
import com.example.trainings.data.response.FullExercise.Companion.toFullExercise
import com.example.trainings.data.response.NetworkService
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
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

            val dboList = body.toExerciseDboList()
            val ids = database.trainingDao().insertExerciseCacheWithId(dboList)
            Log.d("TrainingsDebug", "Сохранено в Room, ids count = ${ids.size}")

            return body.toExerciseList()

        } catch (e: Exception) {
            Log.e("TrainingsDebug", "Ошибка: ${e.message}", e)
            return getCachedExercises() ?: emptyList()
        }
    }

    override suspend fun getCachedExercises(): List<Exercise>? {
        val cache = database.trainingDao().getCache() ?: return null
        return cache.toExerciseListFromDbo()
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

    override suspend fun getFavoriteIds(): List<String> {
        return withContext(Dispatchers.IO) {
            database.trainingDao().getFavoritesByIds()
        }
    }

    override suspend fun getExerciseById(id: String): FullExercise {
        try {
            val cached = database.trainingDao().getExerciseById(id)

            if (cached != null) {
                val isFav = database.trainingDao().isFavorite(id)
                return cached.toExercise()
                    .toFullExercise(getExerciseImageUrl(id), isFav)
            }

            val response = api.getExerciseById(id)
            if (!response.isSuccessful) {
                throw Exception("HTTP ${response.code()}")
            }

            val body = response.body()
                ?: throw Exception("Empty response")

            val dbo = body.toExerciseDbo()
            database.trainingDao().insertExerciseCache(dbo)

            val isFav = database.trainingDao().isFavorite(id)

            return body.toExercise()
                .toFullExercise(getExerciseImageUrl(id), isFav)

        } catch (e: Exception) {
            Log.e("DETAIL_ERROR", "getExerciseById failed", e)
            throw e
        }
    }

    override fun observeFavoriteExercises(): Flow<List<FullExercise>> {
        return database.trainingDao().observeFavoriteExercises()
            .map { list ->
                list.map { dbo ->
                    dbo.toExercise()
                        .toFullExercise(
                            image = getExerciseImageUrl(dbo.id),
                            isFavorite = true
                        )
                }
            }
    }
}
