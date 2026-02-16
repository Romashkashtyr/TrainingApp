package com.example.trainings.data.local.modelsDTO

import com.example.trainings.data.response.WorkoutSession
import com.example.trainings.data.responseAPI.WorkoutSessionAbs
import com.google.gson.annotations.SerializedName

data class WorkoutSessionDTO(
    @SerializedName("id")  override var id: Int = 0,
    @SerializedName("count")  override var count: Int = 10,
    @SerializedName("next")  override var next: String? = null,
    @SerializedName("previous")  override var previous: String? = null,
    @SerializedName("results")  override var results: List<WorkoutSession> = emptyList()
): WorkoutSessionAbs