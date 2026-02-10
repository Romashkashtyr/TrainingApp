package com.example.trainings.data

import com.example.trainings.data.local.modelsDTO.VideoResultTrainingDTO
import com.example.trainings.data.mappers.TrainingMapper.toVideoResultTrainingDBO
import com.example.trainings.data.mappers.TrainingMapper.toVideoResultTrainingDTO
import com.example.trainings.data.response.NetworkService
import com.example.trainings.data.response.TrainingResponse
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.database.TrainingDatabase
import com.example.trainings.database.models.TrainingResponseDBO
import com.example.trainings.database.models.dao.TrainingDAO
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingsRepositoryImpl @Inject constructor(
    private val api: NetworkService,
    private val dao: TrainingDAO,
    private val database: TrainingDatabase
) : TrainingsRepository {


    //    override suspend fun requestTrainingList(): List<Training> {
////        val firebaseFireStore = Firebase.firestore
////        val trainingResult = firebaseFireStore.collection("Trainings").document().get()
////        if (trainingResult.isSuccessful){
////            Status.Success(true)
////        } else {
////            Status.Failure("Failure")
////        }
//////        val firestore = FirebaseFireStore.getInstance()
//////        val firestoreData = firestore.collection("Trainings").document().get()
////
////        return Mapper.mapDataFromFireStoreDB(trainingResult.result)
//
//
//        //val storage = StorageOptions.getDefaultInstance().service
//        TODO()
//    }
//
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
//
//    override fun getAllWorkoutFromServer(query: String): Flow<RequestResult<List<WorkoutSession>>> {
//        val apiRequest =
//            flow { emit(api.getWorkoutSessions()) }
//    }


    override fun observeTrainingResponse(): Flow<TrainingResponse> {
        return dao.observeCache()
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
        val sessionsResult =  api.getWorkoutSessions().map { it.results }
        val videosResult =  api.getVideos().map { it.results }

        if (sessionsResult.isSuccess && videosResult.isSuccess) {
            val workouts = sessionsResult.getOrNull()
            val videos = videosResult.getOrNull()

            dao.insertCache(
                TrainingResponseDBO(
                    id = 1,
                    workouts = workouts,
                    videoResultTraining = videos
                )
            )
        }
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
        database.dao.insertCacheVideo(dbos)

    }
}