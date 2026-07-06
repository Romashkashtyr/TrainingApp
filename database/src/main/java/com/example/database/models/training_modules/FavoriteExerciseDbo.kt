package com.example.database.models.training_modules

import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "favorite_exercises")
data class FavoriteExerciseDbo (
    @PrimaryKey
    val id: String,
//    val name: String?,
//    val description: String,
//    val imageUrl: String,
)