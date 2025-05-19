package com.example.trainingapp.data.api.response

import com.example.trainingapp.data.api.response.responseAPI.VideoResultTrainingAbs
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoResultTraining(
    @SerializedName("id") override var id: Int? = null,
    @SerializedName("uuid") override var uuid: String? = null,
    @SerializedName("exercise") override var exercise: Int? = null,
    @SerializedName("exercise_uuid") override var exerciseUuid: String? = null,
    @SerializedName("video") override var video: String? = null,
    @SerializedName("is_main") override var isMain: Boolean? = null,
    @SerializedName("duration") override var duration: String? = null,
) : Serializable, VideoResultTrainingAbs