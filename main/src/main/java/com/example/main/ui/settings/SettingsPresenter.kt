package com.example.main.ui.settings

import com.example.core.base.BasePresenter
import com.example.core.data.datastore.SettingsDataStore
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : BasePresenter<SettingsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        observeSettings()
    }

    private fun observeSettings() {
        settingsDataStore.notificationsEnabled
            .onEach { viewState.setNotificationsState(it) }
            .launchIn(this)

        settingsDataStore.darkModeEnabled
            .onEach { viewState.setDarkModeState(it) }
            .launchIn(this)
    }

    fun onNotificationChanged(enabled: Boolean) {
        launch {
            settingsDataStore.setNotificationsEnabled(enabled)
        }
    }

    fun onDarkModeChanged(enabled: Boolean) {
        launch {
            settingsDataStore.setDarkModeEnabled(enabled)
        }
    }

    fun onLogoutClicked() {
        viewState.showLogoutConfirmation()
    }
}
