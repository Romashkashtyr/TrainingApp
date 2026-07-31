package com.example.database.models.main_modules.water

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water")
data class WaterDb(
    @PrimaryKey
    val date: String,
    @ColumnInfo(name = "amount")
    val amount: Int,
)
