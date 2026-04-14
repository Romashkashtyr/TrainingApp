package com.example.trainings.data.response

import com.example.trainings.data.local.modelsDTO.ExerciseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("/exercises/")
    suspend fun getAllExercises(
        @Query("name") name: List<String>? = null,
        @Query("description") description: String? = null,
    ): Result<ExerciseDto>


    @GET("/exercises/search")
    suspend fun getExerciseByName(
        @Query("q") query: String? = null,
    ): Result<List<ExerciseDto>>

}