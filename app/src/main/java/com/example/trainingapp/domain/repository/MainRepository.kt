package com.example.trainingapp.domain.repository

import com.example.trainingapp.domain.Status

interface MainRepository {

    suspend fun getWaterAmount(addWater: (Int) -> String, errorCallback: (String) -> (Unit)): Status<Int>
}