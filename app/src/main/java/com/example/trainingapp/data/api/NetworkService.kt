package com.example.trainingapp.data.api

import com.example.trainingapp.data.Constants
import com.example.trainingapp.data.network.Video
import com.example.trainingapp.data.network.WorkoutSession
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("/v2/workoutsession")
    suspend fun getWorkoutSession(
        @Query("count") count: Int,
        @Query("next") next: String?,
        @Query("previous") previous: String?,
        @Query("result") result: ArrayList<String>,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<WorkoutSession>


    @GET("/v2/video")
    suspend fun getVideo(
        @Query("id") id: Int?,
        @Query("uuid") uuid: String?,
        @Query("exercise") exercise: Int?,
        @Query("exercise_uuid") exerciseUuid: String?,
        @Query("video") video: String?,
        @Query("is_main") isMain: Boolean?,
        @Query("duration") duration: String?,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<Video>


}