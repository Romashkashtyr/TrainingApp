package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "exercise")
data class ExerciseDbo(
    @ColumnInfo("count") val count: Int,
    @ColumnInfo("next") val next: String?,
    @ColumnInfo("previous") val previous: String?,
    @ColumnInfo("results") val results: List<ExerciseInfoDbo>
)