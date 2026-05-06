package com.example.trainings.data.local.modelsDTO

import com.example.trainings.data.response.PrimaryMusclesInterface
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrimaryMusclesDto(
    @SerializedName("id") override val id: String,
    @SerializedName("name") override val name: String
) : PrimaryMusclesInterface
