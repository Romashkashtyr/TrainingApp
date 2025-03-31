package com.example.trainingapp.data.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoResultTraining(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("exercise") var exercise: Int? = null,
    @SerializedName("exercise_uuid") var exerciseUuid: String? = null,
    @SerializedName("video") var video: String? = null,
    @SerializedName("is_main") var isMain: Boolean? = null,
    @SerializedName("duration") var duration: String? = null,
): Serializable