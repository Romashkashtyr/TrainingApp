package com.example.trainings.domain.modelsDTO

import com.example.trainings.data.response.WorkoutSessionResult
import com.example.trainings.data.responseAPI.WorkoutSessionAbs
import com.google.gson.annotations.SerializedName

data class WorkoutSessionDTO(
    @SerializedName("count")  override var count: Int = 10,
    @SerializedName("next")  override var next: String? = null,
    @SerializedName("previous")  override var previous: String? = null,
    @SerializedName("results")  override var results: List<WorkoutSessionResult> = emptyList()
): WorkoutSessionAbs