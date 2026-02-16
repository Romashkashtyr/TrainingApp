package com.example.trainings.data

import com.example.trainings.data.local.modelsDTO.VideoResultTrainingDTO
import com.example.trainings.data.mappers.TrainingMapper.toTrainingData
import com.example.trainings.data.mappers.TrainingMapper.toVideoResultTrainingDBO
import com.example.trainings.data.mappers.TrainingMapper.toVideoResultTrainingDTO
import com.example.trainings.data.response.NetworkService
import com.example.trainings.data.response.TrainingData
import com.example.trainings.data.response.TrainingResponse
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.database.TrainingRoomDatabase
import com.example.trainings.database.models.TrainingResponseDBO
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingsRepositoryImpl @Inject constructor(
    private val api: NetworkService,
    private val database: TrainingRoomDatabase
) : TrainingsRepository {


    override fun getAllVideoFromServer(query: String): Flow<RequestResult<List<VideoResultTraining>>> {
        return flow { emit(api.getVideosTraining(exercise = query)) }
            .onEach { result ->
                val response = result
                if (result.isSuccess) saveVideosToCache(
                    response.getOrThrow().map {
                        it.toVideoResultTrainingDTO()
                    }
                )
            }
            .map { it.toRequestResult() }

    }


    // временно
    fun <T : Any> Result<T>.toRequestResult(): RequestResult<T> {
        return when {
            isSuccess -> RequestResult.Success(getOrThrow())
            isFailure -> RequestResult.Error()
            else -> error("Impossible branch")
        }
    }


    override fun observeTrainingResponse(): Flow<RequestResult<List<TrainingData>>> {
        return database.trainingDao().observeCache()
            .filterNotNull()
            .map { cache ->
                TrainingResponse(
                    workouts = cache.workouts ?: emptyList(),
                    videoResultTraining = cache.videoResultTraining ?: emptyList()
                )
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun refresh() {
        val sessionsResult = api.getWorkoutSessions().map { it.results }
        val videosResult = api.getVideos().map { it.results }

        if (sessionsResult.isSuccess && videosResult.isSuccess) {
            val workouts = sessionsResult.getOrNull()
            val videos = videosResult.getOrNull()

            database.trainingDao().insertCache(
                TrainingResponseDBO(
                    id = 1,
                    workouts = workouts,
                    videoResultTraining = videos
                )
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAll(query: String): Flow<RequestResult<List<TrainingData>>> {
        val cachedAllTrainingData: Flow<RequestResult<List<TrainingData>>> = getAllFromDatabase()
        val remoteTrainingData: Flow<RequestResult<List<TrainingData>>> = getAllFromServer(query)

        return cachedAllTrainingData.combine(remoteTrainingData) { cachedResult, remoteResult ->
            when {
                remoteResult is RequestResult.Success -> {
                    remoteResult
                }

                remoteResult is RequestResult.Error && cachedResult is RequestResult.Success -> {
                    cachedResult
                }

                cachedResult is RequestResult.InProgress && remoteResult is RequestResult.InProgress -> {
                    RequestResult.InProgress(cachedResult.data ?: remoteResult.data)
                }

                remoteResult is RequestResult.Error -> {
                    remoteResult
                }

                else -> {
                    cachedResult
                }
            }
        }
            .onStart {
                RequestResult.InProgress(data = null)
            }
            .flatMapLatest { result ->
                if (result is RequestResult.Success) {
                    database.trainingDao().observeAll()
                        .map { dbos ->
                            dbos.map { it.toTrainingData() }
                        }
                        .map { RequestResult.Success(it) }
                } else {
                    flowOf(result)
                }
            }

    }

    private fun getAllFromServer(query: String): Flow<RequestResult<List<TrainingData>>> {
        TODO()
    }

    private fun getAllFromDatabase(): Flow<RequestResult<List<TrainingData>>> {
        TODO()
    }

    override suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    private suspend fun saveVideosToCache(data: List<VideoResultTrainingDTO>) {
        val dbos = data.map { videoDTO -> videoDTO.toVideoResultTrainingDBO() }
        database.trainingDao().insertCacheVideo(dbos)

    }
}