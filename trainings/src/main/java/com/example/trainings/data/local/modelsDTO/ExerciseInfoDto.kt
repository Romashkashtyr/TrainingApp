package com.example.trainings.data.local.modelsDTO

import com.example.trainings.data.response.Equipment
import com.example.trainings.data.response.Muscles
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseInfoDto(
    @SerializedName("id") val id: Int,
    @SerializedName("muscles") val muscles: List<MusclesDto>,
    @SerializedName("equipment") val equipment: List<EquipmentDto>
)
