package com.example.trainings.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment")
data class EquipmentDbo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String? = null
)
