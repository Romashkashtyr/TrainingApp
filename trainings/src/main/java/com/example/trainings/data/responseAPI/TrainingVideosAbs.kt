package com.example.trainings.data.responseAPI

import com.example.trainings.data.response.VideoResultTraining

interface TrainingVideosAbs {
    val count: Int
    val next: String
    val previous: String
    val result: List<VideoResultTraining>
}