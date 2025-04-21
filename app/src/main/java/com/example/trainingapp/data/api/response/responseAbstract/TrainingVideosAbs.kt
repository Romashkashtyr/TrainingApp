package com.example.trainingapp.data.api.response.responseAbstract

import com.example.trainingapp.data.api.response.VideoResultTraining

interface TrainingVideosAbs {
    val count: Int
    val next: String
    val previous: String
    val result: List<VideoResultTraining>
}