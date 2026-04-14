package com.example.trainings.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "primary_muscles")
data class PrimaryMusclesDbo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
