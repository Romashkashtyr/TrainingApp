package com.example.trainings.database.converters

import androidx.room.TypeConverters
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class Converters @Inject constructor(){

    @Inject
    private lateinit var gson: Gson

    @TypeConverters
    fun toWorkoutSessionList(json: String?): List<WorkoutSession>? {
        return json?.let {
            val type = object : TypeToken<List<WorkoutSession>>() {}.type
            gson.fromJson(it, type)
        }
    }

    @TypeConverters
    fun workoutsToJson(list: List<WorkoutSession>?): String? {
        return list?.let { gson.toJson(it)}
    }

    @TypeConverters
    fun videoToJson(list: List<VideoResultTraining>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverters
    fun jsonToVideos(json: String?): List<VideoResultTraining>? {
        return json?.let {
            val type = object : TypeToken<List<VideoResultTraining>>() {}.type
            gson.fromJson(it, type)
        }
    }
}