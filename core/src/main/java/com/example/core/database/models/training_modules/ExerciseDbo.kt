package com.example.core.database.models.training_modules

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Serializable
@Entity(tableName = "exercise")
data class ExerciseDbo(
    @PrimaryKey  val id: String,
    val primaryMuscles: List<PrimaryMusclesDbo>,
    @ColumnInfo("name")  val name: String?,
    val description: String,
)