package com.example.trainingapp

import com.example.trainingapp.data.ConcreteTraining
import com.example.trainingapp.domain.Training
import com.google.firebase.firestore.DocumentSnapshot
import kotlin.time.Duration

object Mapper {

    fun mapDataFromFireStoreDB(document: DocumentSnapshot): List<Training> {
        val trainingList = mutableListOf<Training>()

        val data = document.get("Trainings") as List<*>

        for (i in data) {
            
            val trainingDataItem = ConcreteTraining(
                trainingName  = i as String,
                duration = i as Duration,
                complexity = i as Int
            )
            trainingList.add(trainingDataItem)
        }

        return trainingList
    }
}