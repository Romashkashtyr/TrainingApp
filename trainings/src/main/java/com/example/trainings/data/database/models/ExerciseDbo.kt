package com.example.trainings.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainings.data.response.ExerciseInterface
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "exercise")
data class ExerciseDbo(
    @PrimaryKey  val id: String,
     val primaryMuscles: List<PrimaryMusclesDbo>,
    @ColumnInfo("name")  val name: String?,
     val description: String,
)