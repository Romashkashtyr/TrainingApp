package com.example.trainings.data.response

import com.example.trainings.data.responseAPI.WorkoutSessionAbs
import com.google.gson.annotations.SerializedName

data class WorkoutSession(
    @SerializedName("count") override var count: Int = 10 ,
    @SerializedName("next") override var next: String? = null,
    @SerializedName("previous") override var previous: String? = null,
    @SerializedName("results") override var results: List<WorkoutSession> = emptyList()
) : WorkoutSessionAbs
