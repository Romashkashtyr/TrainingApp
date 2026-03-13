package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment")
data class EquipmentDbo(
    @PrimaryKey val id: Int = 0,
    val name: String? = null
)
