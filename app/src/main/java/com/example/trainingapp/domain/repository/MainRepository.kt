package com.example.trainingapp.domain.repository

interface MainRepository {

    fun getWaterAmount(addWater: (Int) -> String)
}