package com.example.main.domain.repository

import com.example.database.models.main_modules.StepsDb
import kotlinx.coroutines.flow.Flow

interface StepsRepository {

    suspend fun saveCurrentSteps(steps: Int)

    fun observeTodaySteps(date: String): Flow<Int>


    fun observeStepsHistory(): Flow<List<StepsDb>>

    suspend fun saveSteps(date: String, steps: Int)

    suspend fun getDate() :String?

    suspend fun saveDate(date: String)

    suspend fun getInitialSteps(): Float?

    suspend fun saveInitialSteps(value: Float)
}