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

class SettingsFragment : BaseFragment(), SettingsView {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterFactory: SettingsPresenterFactory

    private val presenter by moxyPresenter {
        presenterFactory.createPresenter()
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
        binding.logoutButton.setOnClickListener {
            presenter.onLogoutClicked()
        }
    }

    override fun showLogoutConfirmation() {
        Toast.makeText(requireContext(), "Выход из аккаунта...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}
