package com.example.trainings.data.response

import com.example.trainings.data.local.modelsDTO.ExerciseDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NetworkService {

    @GET("/exercises")
    suspend fun getAllExercises(): Response<List<ExerciseDto>>


    @GET("/exercises/search")
    suspend fun getExerciseByName(
        @Query("q") query: String? = null,
    ): Result<List<ExerciseDto>>

    @GET("/exercises/{id}")
    suspend fun getExerciseById(
        @Path("id") id: String,
    ): Response<ExerciseDto>


    @GET("/exercises/:id/image")
    suspend fun getExerciseImage(
        @Path("id") id: String,
        @Query("format") format: String = "png"
    ): Response<ResponseBody>

}