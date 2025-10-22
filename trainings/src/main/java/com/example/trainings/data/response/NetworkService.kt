package com.example.trainings.data.response

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("/v2/workoutsession")
    suspend fun getWorkoutSessions(
        @Query("count") count: Int = 10,
        @Query("next") next: String? = null,
        @Query("previous") previous: String? = null,
        @Query("result") result: List<String>? = null,
    ): Response<WorkoutSession>


    @GET("/v2/video")
    suspend fun getVideos(
        @Query("id") id: Int? = null,
        @Query("uuid") uuid: String? = null,
        @Query("exercise") exercise: Int? = null,
        @Query("exercise_uuid") exerciseUuid: String? = null,
        @Query("video") video: String? = null,
        @Query("is_main") isMain: Boolean? = null,
        @Query("duration") duration: String? = null,
    ): Response<List<TrainingVideos>>


}