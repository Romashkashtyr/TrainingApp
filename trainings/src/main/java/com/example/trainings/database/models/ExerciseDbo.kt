package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.trainings.data.response.ExerciseInterface
import com.example.trainings.data.response.PrimaryMuscles

@Entity(tableName = "exercise")
data class ExerciseDbo(
    val id: Int,
    val primaryMuscles: List<PrimaryMusclesDbo>,
    @ColumnInfo("name") val musclesName: String,
    val description: String,
)