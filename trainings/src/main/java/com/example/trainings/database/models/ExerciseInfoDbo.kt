package com.example.trainings.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "exercise_info")
data class ExerciseInfoDbo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @Relation(parentColumn = "id", entityColumn = "name")
    val muscles: List<MusclesDbo>,
    @Relation(parentColumn = "id", entityColumn = "name")
    val equipment: List<EquipmentDbo>,
)
