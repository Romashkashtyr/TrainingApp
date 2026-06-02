package com.example.main.domain

import kotlinx.coroutines.flow.Flow

interface StepsRepository {

    suspend fun saveCurrentSteps(steps: Int)

    fun observeSteps(): Flow<Int>

    suspend fun saveSteps(steps: Int)

    suspend fun getDate() :String?

    suspend fun saveDate(date: String)

    suspend fun getInitialSteps(): Float?

    suspend fun saveInitialSteps(value: Float)
}