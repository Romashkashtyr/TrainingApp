package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainings.data.response.ExerciseInterface
import com.example.trainings.data.response.PrimaryMuscles
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "exercise")
data class ExerciseDbo(
    @PrimaryKey val id: String,
    val primaryMuscles: List<PrimaryMusclesDbo>,
    @ColumnInfo("name") val musclesName: String?,
    val description: String,
)