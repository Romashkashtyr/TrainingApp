package com.example.main.ui.settings

import com.example.core.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SettingsView : BaseView {
    fun showLogoutConfirmation()
    fun setNotificationsState(enabled: Boolean)
    fun setDarkModeState(enabled: Boolean)
}
