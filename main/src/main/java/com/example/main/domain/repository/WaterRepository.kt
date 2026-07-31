package com.example.main.domain.repository

import com.example.main.data.entitieModules.Water
import kotlinx.coroutines.flow.Flow

interface WaterRepository {

    suspend fun addWater(amount: Int)

    suspend fun getTodayWater(): Int

    fun observeHistory(): Flow<List<Water>>
}