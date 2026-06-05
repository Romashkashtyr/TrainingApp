package com.example.main.data.repository

import com.example.core.data.datastore.StepsDataStore
import com.example.core.database.models.dao.MainDao
import com.example.core.database.models.main_modules.StepsDb
import com.example.main.domain.repository.StepsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StepsRepositoryImpl @Inject constructor(
    private val dao: MainDao,
    private val stepsDataStore: StepsDataStore
) : StepsRepository {
    override suspend fun saveCurrentSteps(steps: Int) {
        stepsDataStore.saveCurrentSteps(steps)
    }

    override fun observeTodaySteps(date: String): Flow<Int> {
       //return stepsDataStore.observeSteps()
        return dao.observeTodaySteps(date)
    }

    override fun observeStepsHistory(): Flow<List<StepsDb>> {
        return dao.observeStepsHistory()
    }

    override suspend fun saveSteps(date: String, steps: Int) {
       // stepsDataStore.saveCurrentSteps(steps)
        dao.insertOrUpdate(StepsDb(
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