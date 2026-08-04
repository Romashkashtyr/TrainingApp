package com.example.main.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core.utils.getLocalDate
import com.example.database.TrainingRoomDatabase
import com.example.database.models.main_modules.water.WaterDb
import com.example.main.data.MainMapper.toWater
import com.example.main.data.entitieModules.Water
import com.example.main.domain.repository.WaterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WaterRepositoryImpl @Inject constructor (
    private val database: TrainingRoomDatabase
): WaterRepository {

    private val waterDao = database.waterDao()
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun addWater(amount: Int) {
        val today = getLocalDate()

        val currentAmount = waterDao.getWaterByDate(today)

        if (currentAmount == null) {
            waterDao.saveWater(
                WaterDb(
                    date = today,
                    amount = amount
                )
            )
        } else {
            waterDao.saveWater(
                currentAmount.copy(
                    amount = currentAmount.amount + amount
                )
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getTodayWater(): Int {
        val today = getLocalDate()
        return waterDao.getWaterByDate(today)?.amount ?: 0
    }

    override fun observeHistory(): Flow<List<Water>> {
        return waterDao.observeHistory().map { history ->
            history.map { entity ->
                entity.toWater()
            }
        }
    }
}