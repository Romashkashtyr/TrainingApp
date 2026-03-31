package com.example.trainings.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.trainings.database.converters.Converters
import com.google.gson.annotations.SerializedName


//@Entity(tableName = "training")
//data class TrainingDataDbo(
//    @PrimaryKey(autoGenerate = true)
//    var id: Int? = null,
//    var authorHistory: List<String?> = emptyList(),
//    var uuid: String? = null,
//    var exercise: Int? = null,
//    var exerciseUuid: String? = null,
//    var videoUrl: String? = null,
//    var isMain: Boolean? = null,
//    var duration: String? = null,
//)
