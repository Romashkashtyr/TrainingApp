package com.example.trainings.data.response

import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.local.modelsDTO.TrainingDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("/v2/exerciseinfo/")
    suspend fun getExerciseInfo(
        @Query("category") muscles: List<Muscles>? = null,
        @Query("equipment") equipment: List<Equipment>? = null,
        @Query("limit") limit: Int = 20
    ): Result<ExerciseDto>


    @GET("/v2/video")
    suspend fun getData(
        @Query("video") video: String? = null,
        @Query("author_history") authorHistory: List<String>? = null,
    ): Result<List<TrainingDataDto>>

}