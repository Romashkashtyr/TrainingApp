package com.example.trainings.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscles")
data class MusclesDbo(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String?,
    val imageUrlMain: String?,
)
