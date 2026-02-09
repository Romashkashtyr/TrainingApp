package com.example.trainings.data.response

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("/v2/workoutsession")
    suspend fun getWorkoutSessions(
        @Query("count") count: Int = 10,
        @Query("next") next: String? = null,
    ): Response<WorkoutSession>


    @GET("/v2/video")
    suspend fun getVideos(
        @Query("exercise") exercise: String? = null,
        @Query("is_main") isMain: Boolean? = null,
    ): Response<VideosResponse>

    @GET("/v2/video")
    suspend fun getVideosTraining(
        @Query("exercise") exercise: String? = null,
        @Query("is_main") isMain: Boolean? = null,
    ): Result<List<VideoResultTraining>>



}