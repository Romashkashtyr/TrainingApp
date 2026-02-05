package com.example.trainings.data.response

import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<VideoResultTraining>,
)