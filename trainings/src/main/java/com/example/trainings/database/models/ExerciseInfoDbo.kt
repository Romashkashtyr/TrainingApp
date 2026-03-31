package com.example.trainings.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "exercise_info")
data class ExerciseInfoDbo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val muscles: List<MusclesDbo>,
    val equipment: List<EquipmentDbo>,
)
