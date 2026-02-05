package com.example.trainings.domain.modelsDTO

import com.example.trainings.data.responseAPI.WorkoutSessionResultAbs
import com.google.gson.annotations.SerializedName

data class WorkoutSessionResultDTO(
    @SerializedName("id") override val id: Int? = null,
    @SerializedName("day") override val day: Int? = null,
    @SerializedName("timeStart") override val timeStart: String = "00:00",
    @SerializedName("timeEnd") override val timeEnd: String
): WorkoutSessionResultAbs
