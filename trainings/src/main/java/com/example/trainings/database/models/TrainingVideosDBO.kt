package com.example.trainings.database.models

import androidx.room.Entity
import com.example.trainings.data.response.VideoResultTraining
import com.google.gson.annotations.SerializedName

@Entity(tableName = "training_videos")
data class TrainingVideosDBO(
    @SerializedName("count") val count: Int = 10,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("result") val result: List<VideoResultTraining>
)
