package com.example.main.ui.settings

import com.example.core.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SettingsView : BaseView {
    fun showLogoutConfirmation()
}
