package com.example.trainings.data.local.modelsDTO

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ExerciseDto(
    @SerialName("id") val id: String,
    @SerialName("primaryMuscles") val primaryMuscles: List<PrimaryMusclesDto>,
    @SerialName("name") val musclesName: String?,
    @SerialName("description") val description: String,
)