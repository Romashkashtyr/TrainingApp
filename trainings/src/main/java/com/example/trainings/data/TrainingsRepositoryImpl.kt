package com.example.trainings.data

import com.example.trainings.Training
import com.example.trainings.data.response.TrainingVideos
import com.example.trainings.data.response.WorkoutSession
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingsRepositoryImpl @Inject constructor() : TrainingsRepository {



    override suspend fun requestTrainingList(): List<Training> {
//        val firebaseFireStore = Firebase.firestore
//        val trainingResult = firebaseFireStore.collection("Trainings").document().get()
//        if (trainingResult.isSuccessful){
//            Status.Success(true)
//        } else {
//            Status.Failure("Failure")
//        }
////        val firestore = FirebaseFireStore.getInstance()
////        val firestoreData = firestore.collection("Trainings").document().get()
//
//        return Mapper.mapDataFromFireStoreDB(trainingResult.result)


        //val storage = StorageOptions.getDefaultInstance().service
        TODO()
    }

    override fun getAllVideoFromServer(query: String): Flow<RequestResult<List<TrainingVideos>>> {
        TODO("Not yet implemented")
    }

    override fun getAllWorkoutFromServer(query: String): Flow<RequestResult<List<WorkoutSession>>> {
        TODO("Not yet implemented")
    }


}