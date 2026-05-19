package com.example.trainings.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "primary_muscles")
data class PrimaryMusclesDbo(
    @PrimaryKey val id: String,
    val name: String
)
