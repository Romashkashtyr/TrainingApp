package com.example.database.models.main_modules

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "steps_history")
data class StepsDb(
    @PrimaryKey val date: String,
    val steps: Int
)