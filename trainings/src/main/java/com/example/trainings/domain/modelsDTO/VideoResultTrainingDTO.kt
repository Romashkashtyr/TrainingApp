package com.example.trainings.domain.modelsDTO

import com.example.trainings.data.responseAPI.VideoResultTrainingAbs
import com.google.gson.annotations.SerializedName

data class VideoResultTrainingDTO(
    @SerializedName("id") override var id: Int? = null,
    @SerializedName("uuid") override var uuid: String? = null,
    @SerializedName("exercise") override var exercise: Int? = null,
    @SerializedName("exercise_uuid") override var exerciseUuid: String? = null,
    @SerializedName("video") override var video: String? = null,
    @SerializedName("is_main") override var isMain: Boolean? = null,
    @SerializedName("duration") override var duration: String? = null,
) : VideoResultTrainingAbs