package com.example.trainings.data.local.modelsDTO

import com.example.trainings.data.response.PrimaryMusclesInterface
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrimaryMusclesDto(
    @SerialName("id") override val id: String,
    @SerialName("name") override val name: String
) : PrimaryMusclesInterface
