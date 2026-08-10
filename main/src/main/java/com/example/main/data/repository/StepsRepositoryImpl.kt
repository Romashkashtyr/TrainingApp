package com.example.main.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core.data.datastore.StepsDataStore
import com.example.core.utils.getLocalDate
import com.example.database.TrainingRoomDatabase
import com.example.database.models.main_modules.StepsDb
import com.example.main.data.MainMapper.toSteps
import com.example.main.data.entitieModules.Steps
import com.example.main.domain.repository.StepsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StepsRepositoryImpl @Inject constructor(
    private val database: TrainingRoomDatabase,
    private val stepsDataStore: StepsDataStore
) : StepsRepository {

    override suspend fun saveCurrentSteps(steps: Int) {
        stepsDataStore.saveCurrentSteps(steps)
    }

    override fun observeTodaySteps(date: String): Flow<Int?> {
       //return stepsDataStore.observeSteps()
        return database.mainDao().observeTodaySteps(date)
    }

    override fun observeStepsHistory(): Flow<List<Steps>> {
        return database.mainDao().observeStepsHistory()
            .map { history ->
                history.map { entity ->
                    entity.toSteps()
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getTodaySteps(): Int {
        val today = getLocalDate()

        return database
            .mainDao()
            .getTodaySteps(today)

    }

    override suspend fun saveSteps(date: String, steps: Int) {
        database.mainDao().insertOrUpdate(StepsDb(
            date = date,
            steps = steps
        ))
    }

    override suspend fun getDate(): String? {
        return stepsDataStore.getDate()
    }

    override suspend fun saveDate(date: String) {
        stepsDataStore.saveDate(date)
    }

    override suspend fun getInitialSteps(): Float? {
        return stepsDataStore.getInitialSteps()
    }

    override suspend fun saveInitialSteps(value: Float) {
        stepsDataStore.saveInitialSteps(value)
    }


}