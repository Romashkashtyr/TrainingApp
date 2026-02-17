package com.example.trainings.data.response

import com.example.trainings.data.responseAPI.TrainingDataAbs
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TrainingData(
    @SerializedName("id") override var id: Int? = null,
    @SerializedName("author_history") override var authorHistory: List<String?> = emptyList(),
    @SerializedName("uuid") override var uuid: String? = null,
    @SerializedName("exercise") override var exercise: Int? = null,
    @SerializedName("exercise_uuid") override var exerciseUuid: String? = null,
    @SerializedName("video") override var videoUrl: String? = null,
    @SerializedName("is_main") override var isMain: Boolean? = null,
    @SerializedName("duration") override var duration: String? = null,
) : TrainingDataAbs
