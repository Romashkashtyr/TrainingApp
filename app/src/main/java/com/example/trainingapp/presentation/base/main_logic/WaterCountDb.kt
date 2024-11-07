package com.example.trainingapp.presentation.base.main_logic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water")
data class WaterCountDb(
    @PrimaryKey @ColumnInfo(name = "water_count") val waterCountDb: Int
)
