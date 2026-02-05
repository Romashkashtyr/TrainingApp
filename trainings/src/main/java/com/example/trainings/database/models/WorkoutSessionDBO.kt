package com.example.trainings.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainings.data.response.WorkoutSessionResult
import com.example.trainings.data.responseAPI.WorkoutSessionAbs
import com.google.gson.annotations.SerializedName


@Entity(tableName = "workout_sessions")
data class WorkoutSessionDBO(
    @PrimaryKey(autoGenerate = true) var id: Int,
    override var count: Int = 10,
    override var next: String? = null,
    override var previous: String? = null,
    override var results: List<WorkoutSessionResult>
): WorkoutSessionAbs
