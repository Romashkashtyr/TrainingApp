package com.example.main.data.repository

import com.example.core.data.datastore.StepsDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StepsRepositoryImpl @Inject constructor(
    private val stepsDataStore: StepsDataStore
) : com.example.main.domain.StepsRepository {
    override suspend fun saveCurrentSteps(steps: Int) {
        stepsDataStore.saveCurrentSteps(steps)
    }

    override fun observeSteps(): Flow<Int> {
       return stepsDataStore.observeSteps()
    }

    override suspend fun saveSteps(steps: Int) {
        stepsDataStore.saveCurrentSteps(steps)
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