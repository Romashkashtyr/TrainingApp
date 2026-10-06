package com.example.main.ui.settings

import com.example.core.base.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor() : BasePresenter<SettingsView>() {

    fun onLogoutClicked() {
        viewState.showLogoutConfirmation()
    }
}
