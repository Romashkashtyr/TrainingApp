package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.trainings.data.response.Equipment
import com.example.trainings.data.response.Muscles

@Entity(tableName = "exercise_info")
data class ExerciseInfoDbo(
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("muscles") val muscles: List<MusclesDbo>,
    @ColumnInfo("equipment") val equipment: List<EquipmentDbo>
)
