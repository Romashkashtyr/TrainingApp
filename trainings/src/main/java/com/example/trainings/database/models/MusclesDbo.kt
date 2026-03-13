package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscles")
data class MusclesDbo(
    @PrimaryKey val name: String?,
    val imageUrlMain: String?
)
