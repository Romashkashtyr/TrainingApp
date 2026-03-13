package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainings.data.response.Equipment
import com.example.trainings.data.response.Muscles

@Entity(tableName = "exercise_info")
data class ExerciseInfoDbo(
    @PrimaryKey val id: Int,
    val muscles: List<MusclesDbo>,
    val equipment: List<EquipmentDbo>
)
