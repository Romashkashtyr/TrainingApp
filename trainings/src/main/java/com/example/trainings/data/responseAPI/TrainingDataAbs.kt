package com.example.trainings.data.responseAPI

import com.google.gson.annotations.SerializedName

interface TrainingDataAbs {
    var id: Int?
    var authorHistory: List<String?>
    var uuid: String?
    var exercise: Int?
    var exerciseUuid: String?
    var videoUrl: String?
    var isMain: Boolean?
    var duration: String?
}