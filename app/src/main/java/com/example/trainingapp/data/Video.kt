package com.example.trainingapp.data

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("exercise") var exercise: Int? = null,
    @SerializedName("exercise_uuid") var exerciseUuid: String? = null,
    @SerializedName("video") var video: String? = null,
    @SerializedName("is_main") var isMain: Boolean? = null,
    @SerializedName("duration") var duration: String? = null,
)
