package com.example.trainings.database.converters

import androidx.room.TypeConverter
import com.example.trainings.data.response.VideoResultTraining
import com.example.trainings.data.response.WorkoutSession
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


class Converters {


    @Inject
    private lateinit var json: Json

    @TypeConverter
    fun jsonToWorkoutSessionList(jsonString: String?): List<WorkoutSession>? {
        return jsonString?.let {
            json.decodeFromString<List<WorkoutSession>>(it)
        }
    }

    @TypeConverter
    fun workoutsToJson(list: List<WorkoutSession>?): String? {
        return list?.let { json.encodeToString(it)}
    }

    @TypeConverter
    fun videoToJson(list: List<VideoResultTraining>?): String? {
        return list?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun jsonToVideos(jsonString: String?): List<VideoResultTraining>? {
        return jsonString?.let {
            json.decodeFromString(it)
        }
    }
}