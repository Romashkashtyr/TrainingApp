package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession
import com.example.trainings.database.converters.Converters
import com.google.gson.annotations.SerializedName


@Entity(tableName = "training")
data class TrainingDataDbo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")  var id: Int? = null,
    @ColumnInfo("uuid")  var uuid: String? = null,
    @ColumnInfo("exercise")  var exercise: Int? = null,
    @ColumnInfo("exercise_uuid")  var exerciseUuid: String? = null,
    @ColumnInfo("video")  var videoUrl: String? = null,
    @ColumnInfo("is_main")  var isMain: Boolean? = null,
    @ColumnInfo("duration")  var duration: String? = null,
)
