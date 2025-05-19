package com.example.trainingapp.data.api.response

import com.example.trainingapp.data.api.response.responseAPI.TrainingVideosAbs

data class TrainingVideos(
    override val count: Int,
    override val next: String,
    override val previous: String,
    override val result: List<VideoResultTraining>
) : TrainingVideosAbs
