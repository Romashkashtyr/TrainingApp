package com.example.trainingapp.domain.repository

import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.Status
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getWaterAmount(): Flow<Status<Int>>
    suspend fun addWater(amount: Int)
}