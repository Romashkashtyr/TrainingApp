package com.example.trainingapp.data.network

import com.google.gson.annotations.SerializedName

data class WorkoutSession(
    @SerializedName("count") override var count: Int = 0,
    @SerializedName("next") override var next: String? = null,
    @SerializedName("previous") override var previous: String? = null,
    @SerializedName("results") override var results: List<WorkoutSessionResult> = emptyList()
) : WorkoutSessionAbs
