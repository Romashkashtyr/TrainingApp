package com.example.trainings.data.response

import com.example.trainings.data.responseAPI.VideoResultTrainingAbs
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResultTraining(
    @SerializedName("id") override var id: Int? = null,
    @SerializedName("uuid") override var uuid: String? = null,
    @SerializedName("exercise") override var exercise: Int? = null,
    @SerializedName("exercise_uuid") override var exerciseUuid: String? = null,
    @SerializedName("video") override var videoUrl: String? = null,
    @SerializedName("is_main") override var isMain: Boolean? = null,
    @SerializedName("duration") override var duration: String? = null,
) :VideoResultTrainingAbs