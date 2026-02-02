package com.example.trainings.domain.modelsDTO

import com.google.gson.annotations.SerializedName

data class WorkoutSessionResultDTO(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("day") val day: Int? = null,
    @SerializedName("timeStart") val timeStart: String = "00:00",
    @SerializedName("timeEnd") val timeEnd: String
)
