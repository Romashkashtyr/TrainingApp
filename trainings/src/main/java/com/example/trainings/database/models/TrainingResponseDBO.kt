package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession
import com.example.trainings.database.converters.Converters


// удалить
@Entity(tableName = "training")
@TypeConverters(Converters::class)
data class TrainingResponseDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("workouts")
    val workouts: List<WorkoutSession>? = null,
    @ColumnInfo("videoResultTraining")
    val videoResultTraining: List<VideoResultTraining>? = null
)
