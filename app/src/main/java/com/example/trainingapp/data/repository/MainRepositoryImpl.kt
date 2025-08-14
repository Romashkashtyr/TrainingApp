package com.example.trainingapp.data.repository

import com.example.trainingapp.Constants
import com.example.trainingapp.data.exception.FirebaseExceptionCatcher
import com.example.trainingapp.domain.Status
import com.example.trainingapp.domain.repository.MainRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class MainRepositoryImpl : MainRepository {

    private val databaseReference = FirebaseDatabase
        .getInstance(Constants.DATABASE)
        .reference
    private val databaseWater = databaseReference.child(Constants.DATABASE_REFERENCE_PATH)
    private val catcher = FirebaseExceptionCatcher()


    override suspend fun getWaterAmount(): Status<Int> {
        return catcher.launchWithCatch {
            val snapshot = databaseWater.get().await()
            if (snapshot.exists()) {
                val waterAmount = snapshot.getValue(Int::class.java) ?: 0
                Status.Success(waterAmount)
            } else {
                Status.Success(0)
            }

        }
    }

    override suspend fun addWater(amount: Int): Status<Unit> {
        return catcher.launchWithCatch {
            val snapshot = databaseWater.get().await()
            val currentAmount = snapshot.getValue(Int::class.java) ?: 0
            val newAmount = currentAmount + amount
            databaseWater.setValue(newAmount.toString()).await()
            Status.Success(Unit)
        }

    }

}