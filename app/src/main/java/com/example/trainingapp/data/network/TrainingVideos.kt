package com.example.trainingapp.data.network

data class TrainingVideos(
    val count: Int,
    val next: String,
    val previous: String,
    val result: List<VideoResultTraining>
)
