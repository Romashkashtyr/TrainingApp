package com.example.trainingapp.data

import com.example.trainingapp.domain.repository.MainRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainRepositoryImpl : MainRepository {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("get_water")

    override fun getWaterAmount(addWater: (Int) -> String) {
        TODO("Not yet implemented")
    }
}