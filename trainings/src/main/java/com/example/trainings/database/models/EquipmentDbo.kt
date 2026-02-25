package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment")
data class EquipmentDbo(
    @PrimaryKey @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("name") val name: String? = null
)
