package com.example.trainings.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "video_results")
data class VideoResultTrainingDBO(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var uuid: String? = null,
    var exercise: Int? = null,
    var exerciseUuid: String? = null,
    var video: String? = null,
    var isMain: Boolean? = null,
    var duration: String? = null,
)
