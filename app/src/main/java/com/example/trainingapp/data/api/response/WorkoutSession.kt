package com.example.trainingapp.data.api.response

import com.example.trainingapp.data.api.response.responseAPI.WorkoutSessionAbs
import com.google.gson.annotations.SerializedName

data class WorkoutSession(
    @SerializedName("count") override var count: Int ,
    @SerializedName("next") override var next: String? = null,
    @SerializedName("previous") override var previous: String? = null,
    @SerializedName("results") override var results: List<WorkoutSessionResult> = emptyList()
) : WorkoutSessionAbs
