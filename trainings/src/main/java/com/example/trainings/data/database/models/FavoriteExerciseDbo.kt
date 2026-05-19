package com.example.trainings.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_exercises")
data class FavoriteExerciseDbo (
    @PrimaryKey
    val id: String,
    val name: String?,
    val description: String,
    val imageUrl: String
)