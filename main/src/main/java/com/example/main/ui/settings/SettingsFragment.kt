package com.example.main.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.core.base.BaseFragment
import com.example.main.databinding.FragmentSettingsBinding
import com.example.main.di.MainComponent
import com.example.main.di.modules.SettingsPresenterFactory

import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SettingsFragment : BaseFragment(), SettingsView {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterFactory: Provider<SettingsPresenter>

    private val presenter by moxyPresenter {
        presenterFactory.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainComponent.getMainInstance().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.notificationsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            presenter.onNotificationChanged(isChecked)
        }

        binding.darkModeCheckbox.setOnCheckedChangeListener { _, isChecked ->
            presenter.onDarkModeChanged(isChecked)
        }

        binding.logoutButton.setOnClickListener {
            presenter.onLogoutClicked()
        }
    }

    override fun setNotificationsState(enabled: Boolean) {
        binding.notificationsCheckbox.isChecked = enabled
    }

    override fun setDarkModeState(enabled: Boolean) {
        binding.darkModeCheckbox.isChecked = enabled
    }

    override fun showLogoutConfirmation() {
        showToast("Выход из аккаунта")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}
