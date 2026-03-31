package com.example.trainings.database.converters

import androidx.room.TypeConverter
import com.example.trainings.database.models.EquipmentDbo
import com.example.trainings.database.models.ExerciseInfoDbo
import com.example.trainings.database.models.MusclesDbo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


class Converters {

    @Inject
     internal lateinit var json: Json

    @TypeConverter
    fun fromExerciseInfoList(list: List<ExerciseInfoDbo>?): String {
        return json.encodeToString(list ?: emptyList<ExerciseInfoDbo>())
    }

    @TypeConverter
    fun toExerciseInfoList(jsonString: String?): List<ExerciseInfoDbo> {
        return jsonString?.let {
            json.decodeFromString<List<ExerciseInfoDbo>>(it)
        } ?: emptyList()
    }


    @TypeConverter
    fun fromMusclesList(list: List<MusclesDbo>?): String {
        return json.encodeToString(list ?: emptyList<MusclesDbo>())
    }

    @TypeConverter
    fun toMusclesList(jsonString: String?): List<MusclesDbo> {
        return jsonString?.let {
            json.decodeFromString<List<MusclesDbo>>(it)
        } ?: emptyList()
    }

    @TypeConverter
    fun fromEquipmentList(list: List<EquipmentDbo>?): String {
        return json.encodeToString(list ?: emptyList<EquipmentDbo>())
    }

    @TypeConverter
    fun toEquipmentList(jsonString: String?): List<EquipmentDbo> {
        return jsonString?.let {
            json.decodeFromString<List<EquipmentDbo>>(it)
        } ?: emptyList()
    }

}