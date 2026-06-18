package com.example.database.models.training_modules

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "primary_muscles")
data class PrimaryMusclesDbo(
    @PrimaryKey  val id: String,
     val name: String
)
