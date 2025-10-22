package com.example.trainings.data.response

import com.example.trainings.data.responseAPI.TrainingVideosAbs

data class TrainingVideos(
    override val count: Int = 10,
    override val next: String,
    override val previous: String,
    override val result: List<VideoResultTraining>
) : TrainingVideosAbs
