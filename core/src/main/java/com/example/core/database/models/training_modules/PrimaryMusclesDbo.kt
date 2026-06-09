package com.example.core.database.models.training_modules

import androidx.room.Entity
import androidx.room.PrimaryKey

@Serializable
@Entity(tableName = "primary_muscles")
data class PrimaryMusclesDbo(
    @PrimaryKey  val id: String,
     val name: String
)
