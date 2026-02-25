package com.example.trainings.data.local.modelsDTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EquipmentDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String? = null
)
