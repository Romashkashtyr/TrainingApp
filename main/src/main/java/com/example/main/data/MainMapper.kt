package com.example.main.data

import com.example.database.models.main_modules.StepsDb
import com.example.database.models.main_modules.WorkoutHistoryDb
import com.example.database.models.main_modules.water.WaterDb
import com.example.main.data.entitieModules.Steps
import com.example.main.data.entitieModules.Water
import com.example.main.data.entitieModules.WorkoutHistory
import com.example.main.data.entitieModules.WorkoutLevel

object MainMapper {

    fun WaterDb.toWater() = Water(
        date = date,
        amount = amount
    )

    fun Water.toWaterDb() = WaterDb(
        date = date,
        amount = amount
    )

    fun StepsDb.toSteps() = Steps(
        date = date,
        steps = steps
    )

    fun Steps.toStepsDb() = StepsDb(
        date = date,
        steps = steps
    )

    fun WorkoutHistoryDb.toWorkoutHistory(): WorkoutHistory {
        return WorkoutHistory(
            id = id,
            workoutId = workoutId,
            workoutName = workoutName,
            level = WorkoutLevel.valueOf(level),
            date = date
        )
    }
}