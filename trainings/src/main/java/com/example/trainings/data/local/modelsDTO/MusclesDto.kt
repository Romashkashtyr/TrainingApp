package com.example.trainings.data.local.modelsDTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MusclesDto(
    @SerializedName("name") val name: String?,
    @SerializedName("imageUrlMain") val imageUrlMain: String?
)
