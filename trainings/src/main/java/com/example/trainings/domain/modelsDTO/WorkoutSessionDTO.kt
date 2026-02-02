package com.example.trainings.domain.modelsDTO

import com.example.trainings.data.response.WorkoutSessionResult
import com.google.gson.annotations.SerializedName

data class WorkoutSessionDTO(
    @SerializedName("count")  var count: Int = 10,
    @SerializedName("next")  var next: String? = null,
    @SerializedName("previous")  var previous: String? = null,
    @SerializedName("results")  var results: List<WorkoutSessionResult> = emptyList()
)