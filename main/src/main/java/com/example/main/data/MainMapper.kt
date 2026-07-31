package com.example.main.data

import com.example.database.models.main_modules.water.WaterDb
import com.example.main.data.entitieModules.Water

object MainMapper {

    fun WaterDb.toWater() = Water(
        date = date,
        amount = amount
    )

    fun Water.toWaterDb() = WaterDb(
        date = date,
        amount = amount
    )
}