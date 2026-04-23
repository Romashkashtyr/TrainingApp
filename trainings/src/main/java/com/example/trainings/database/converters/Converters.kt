package com.example.trainings.database.converters

import androidx.room.TypeConverter
import com.example.trainings.data.response.PrimaryMuscles
import com.example.trainings.database.models.ExerciseDbo
import com.example.trainings.database.models.PrimaryMusclesDbo
import com.google.gson.Gson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


class Converters {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val gson = Gson()

    @TypeConverter
    fun fromExerciseInfoList(list: List<ExerciseDbo>?): String {
        return json.encodeToString(list ?: emptyList<ExerciseDbo>())
    }


    @TypeConverter
    fun fromMusclesList(list: List<PrimaryMusclesDbo>?): String {
        return json.encodeToString(list ?: emptyList<PrimaryMusclesDbo>())
    }

    @TypeConverter
    fun toMusclesList(jsonString: String?): List<PrimaryMusclesDbo> {
        return jsonString?.let {
            json.decodeFromString<List<PrimaryMusclesDbo>>(it)
        } ?: emptyList()
    }




}