package com.example.trainingapp.data.repository

import com.example.trainingapp.domain.Training
import com.example.trainingapp.domain.repository.TrainingsRepository
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


}