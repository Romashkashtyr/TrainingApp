package com.example.trainings.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainings.data.response.WorkoutSessionResult
import com.google.gson.annotations.SerializedName


@Entity(tableName = "workout_sessions")
data class WorkoutSessionDBO(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var count: Int = 10,
    var next: String? = null,
    var previous: String? = null
)
