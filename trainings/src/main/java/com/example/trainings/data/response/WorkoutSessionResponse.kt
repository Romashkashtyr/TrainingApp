package com.example.trainings.data.response

import com.example.trainings.domain.modelsDTO.WorkoutSessionDTO

data class WorkoutSessionResponse(
    var count: Int = 10,
    var next: String? = null,
    var previous: String? = null,
    var results: List<WorkoutSessionDTO> = emptyList()
)
