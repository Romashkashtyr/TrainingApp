package com.example.trainings.data.local.modelsDTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ExerciseDto(
    @SerializedName("id") val id: String,
    @SerializedName("primaryMuscles") val primaryMuscles: List<PrimaryMusclesDto>,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String,
)