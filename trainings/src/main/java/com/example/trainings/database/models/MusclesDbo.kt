package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "muscles")
data class MusclesDbo(
    @ColumnInfo("name") val name: String?,
    @ColumnInfo("imageUrlMain") val imageUrlMain: String?
)
