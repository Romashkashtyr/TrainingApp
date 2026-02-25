package com.example.trainings.data.response

import com.example.trainings.data.local.modelsDTO.ExerciseDto
import com.example.trainings.data.local.modelsDTO.TrainingDataDto
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("/v2/workoutsession")
    suspend fun getWorkoutSessions(
        @Query("count") count: Int = 10,
        @Query("next") next: String? = null,
    ): Result<WorkoutSession>


    @GET("/v2/video")
    suspend fun getVideos(
        @Query("exercise") exercise: String? = null,
        @Query("is_main") isMain: Boolean? = null,
    ): Result<VideosResponse>


    @GET("/v2/exerciseinfo")
    suspend fun getExerciseInfo(
        @Query("category") muscles: List<Muscles>? = null,
        @Query("equipment") equipment: List<Equipment>? = null,
    ): Result<ExerciseDto>


    @GET("/v2/video")
    suspend fun getData(
        @Query("video") video: String? = null,
        @Query("author_history") authorHistory: List<String>? = null,
    ): Result<List<TrainingDataDto>>

    @GET("/v2/video")
    suspend fun getVideosTraining(
        @Query("exercise") exercise: String? = null,
        @Query("is_main") isMain: Boolean? = null,
    ): Result<List<VideoResultTraining>>



}