package com.example.trainingapp.data.network

interface TrainingVideosAbs {
    val count: Int
    val next: String
    val previous: String
    val result: List<VideoResultTraining>
}