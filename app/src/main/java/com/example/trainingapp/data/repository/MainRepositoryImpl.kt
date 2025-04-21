package com.example.trainingapp.data.repository

import com.example.trainingapp.data.exception.FirebaseExceptionCatcher
import com.example.trainingapp.domain.Status
import com.example.trainingapp.domain.repository.MainRepository
import com.google.firebase.FirebaseException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class MainRepositoryImpl : MainRepository {

    private val databaseReference = FirebaseDatabase.getInstance()
    private val databaseWater = databaseReference.getReference("get_water")
    private val catcher = FirebaseExceptionCatcher()


    override suspend fun getWaterAmount(): Status<Int> {
        return catcher.launchWithCatch {
            Status.Success(
                databaseWater.get().await().getValue(Int::class.java) ?: 0
            )
        }
    }

    override suspend fun addWater(amount: Int) {
        try {
            val snapshot = databaseWater.get().await()
            val currentAmount = snapshot?.value.toString().toIntOrNull() ?: 0
            databaseWater.setValue(currentAmount)
        } catch (e: FirebaseException) {

        }


    }


}