package com.example.core.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "settings_prefs")

class SettingsDataStore @Inject constructor(
    private val context: Context
) {
    companion object {
        private val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        private val DARK_MODE_ENABLED = booleanPreferencesKey("dark_mode_enabled")
    }

    val notificationsEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[NOTIFICATIONS_ENABLED] ?: true }

    val darkModeEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[DARK_MODE_ENABLED] ?: false }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_ENABLED] = enabled
        }
    }

    suspend fun setDarkModeEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_ENABLED] = enabled
        }
    }
}
