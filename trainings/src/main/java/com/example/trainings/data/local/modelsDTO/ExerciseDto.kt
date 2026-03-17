package com.example.trainings.data.local.modelsDTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDto(
    @SerializedName("id") val id: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<ExerciseInfoDto>
)