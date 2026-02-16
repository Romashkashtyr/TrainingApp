package com.example.trainings.data.local.modelsDTO

import com.example.trainings.data.response.VideoResultTraining

data class VideosResponseDto(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<VideoResultTraining> = emptyList(),
)