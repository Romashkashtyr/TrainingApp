package com.example.trainingapp.data

import com.example.trainingapp.domain.Status
import com.example.trainingapp.domain.repository.MainRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class MainRepositoryImpl : MainRepository {

    private val databaseReference = FirebaseDatabase.getInstance()
    private val databaseWater = databaseReference.getReference("get_water")
    val catcher = FirebaseExceptionCatcher()

    override suspend fun getWaterAmount(
        addWater: (Int) -> String,
        errorCallback: (String) -> Unit
    ): Status<Int> {
        return catcher.launchWithCatch {
            return@launchWithCatch Status.Success(
                databaseWater.get().await().getValue(Int::class.java) ?: 0
            )
        }
    }
}