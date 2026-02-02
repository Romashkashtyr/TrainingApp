package com.example.trainings.domain.modelsDTO

import com.google.gson.annotations.SerializedName

data class VideoDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("exercise") val exerciseNumber: Int,
    @SerializedName("video") val video: String,
    @SerializedName("is_main") val isMain: Boolean,
    @SerializedName("duration") val duration: String,
)