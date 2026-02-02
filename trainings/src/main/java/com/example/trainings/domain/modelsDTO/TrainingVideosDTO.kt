package com.example.trainings.domain.modelsDTO

import com.example.trainings.data.response.VideoResultTraining
import com.google.gson.annotations.SerializedName

data class TrainingVideosDTO(
    @SerializedName("count") val count: Int = 10,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("result") val result: List<VideoResultTraining>
)
