package com.example.trainingapp.data.network

data class TrainingVideos(
    override val count: Int,
    override val next: String,
    override val previous: String,
    override val result: List<VideoResultTraining>
) : TrainingVideosAbs
