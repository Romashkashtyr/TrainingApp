package com.example.trainingapp.domain.repository

interface MainRepository {

    suspend fun getWaterAmount(): Status<Int>
    suspend fun addWater(amount: Int): Status<Unit>
}