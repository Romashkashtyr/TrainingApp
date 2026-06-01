package com.example.core.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(
    name = "steps_prefs"
)

class StepsDataStore @Inject constructor(
    private val context: Context
) {


    companion object {
        private val INITIAL_STEPS = floatPreferencesKey("initial_steps")

        private val CURRENT_STEPS = intPreferencesKey("current_steps")

        private val LAST_DATE = stringPreferencesKey("last_date")
    }

    suspend fun saveInitialSteps(value: Float) {
        context.dataStore.edit {
            it[INITIAL_STEPS] = value
        }
    }

    suspend fun getInitialSteps(): Float? {
        return context.dataStore.data.first()[INITIAL_STEPS]
    }
    suspend fun saveCurrentSteps(value: Int)  {
         context.dataStore.edit {
            it[CURRENT_STEPS] = value
        }
    }

    suspend fun getCurrentSteps(): Int {
        return context.dataStore.data.first()[CURRENT_STEPS] ?: 0
    }


    suspend fun saveDate(date: String) {
        context.dataStore.edit {
            it[LAST_DATE] = date
        }
    }

    suspend fun getDate(): String? {
        return context.dataStore.data.first()[LAST_DATE]
    }
}