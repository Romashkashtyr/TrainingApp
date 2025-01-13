package com.example.trainingapp.data

import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.Status
import com.example.trainingapp.domain.repository.MainRepository
import com.example.trainingapp.presentation.base.BasePresenter
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

class MainRepositoryImpl : MainRepository {

    private val databaseReference = FirebaseDatabase.getInstance()
    private val databaseWater = databaseReference.getReference("get_water")
    private val catcher = FirebaseExceptionCatcher()


    override suspend fun getWaterAmount(): Flow<Status<Int>> {
        return flow {
            val data = Status.Success(
                databaseWater.get().await().getValue(Int::class.java) ?: 0
            )
            emit(data)
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