package com.example.trainingapp.data.network

import com.google.gson.annotations.SerializedName

interface VideoResultTrainingAbs {
    var id: Int?
    var uuid: String?
    var exercise: Int?
    var exerciseUuid: String?
    var video: String?
    var isMain: Boolean?
    var duration: String?
}