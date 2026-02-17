package com.example.trainings.data.local.modelsDTO

import com.example.trainings.data.response.VideoResultTraining
import com.google.gson.annotations.SerializedName

data class TrainingDataDto(
    @SerializedName("id")  var id: Int? = null,
    @SerializedName("author_history") var authorHistory: List<String?> = emptyList(),
    @SerializedName("uuid")  var uuid: String? = null,
    @SerializedName("exercise")  var exercise: Int? = null,
    @SerializedName("exercise_uuid")  var exerciseUuid: String? = null,
    @SerializedName("video")  var videoUrl: String? = null,
    @SerializedName("is_main")  var isMain: Boolean? = null,
    @SerializedName("duration")  var duration: String? = null,
)
