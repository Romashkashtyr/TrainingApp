package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class ExerciseDbo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<ExerciseInfoDbo>?,
)