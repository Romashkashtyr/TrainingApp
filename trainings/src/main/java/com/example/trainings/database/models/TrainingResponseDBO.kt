package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession



// удалить
@Entity(tableName = "training")
data class TrainingResponseDBO(
     @PrimaryKey(autoGenerate = true) val id: Int,
     @ColumnInfo("workouts") val workouts: List<WorkoutSession>,
     @ColumnInfo("videoResultTraining") val videoResultTraining: List<VideoResultTraining>
)
