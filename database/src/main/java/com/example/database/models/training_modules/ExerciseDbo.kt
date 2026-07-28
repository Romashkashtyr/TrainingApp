package com.example.database.models.training_modules

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
@Entity(tableName = "exercise")
data class ExerciseDbo(
    @PrimaryKey  val id: String,
    val primaryMuscles: List<PrimaryMusclesDbo>,
    @ColumnInfo("name")  val name: String?,
    val description: String,
    val isFavorite: Boolean = false
)