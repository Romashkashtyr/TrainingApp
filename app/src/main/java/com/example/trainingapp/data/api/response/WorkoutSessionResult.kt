package com.example.trainingapp.data.api.response

import com.example.trainingapp.data.api.response.responseAbstract.WorkoutSessionResultAbs

data class WorkoutSessionResult(
    override val id: Int? = null,
    override val day: Int? = null,
    override val timeStart: String = "00:00",
    override val timeEnd: String
) : WorkoutSessionResultAbs
