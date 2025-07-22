package com.example.trainingapp.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.trainingapp.R
import com.example.trainingapp.data.AuthMode
import com.example.trainingapp.databinding.ActivitySignInBinding
import com.example.trainingapp.domain.di.modules.PresenterFactory
import com.example.trainingapp.presentation.TrainingApp
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.main.MainActivity
import com.example.trainingapp.presentation.trainings.TrainingsListActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class AuthorizationActivity : BaseActivity(), AuthorizationView {

    private lateinit var binding: ActivitySignInBinding

    @Inject
    lateinit var presenterFactory: PresenterFactory
    private val presenter by moxyPresenter { presenterFactory.createAuthorizationPresenter() }

    private var mode = AuthMode.LOGIN

    private val userEmail: String
        get() = binding.emailEditText.text.toString()
    private val userPassword: String
        get() = binding.enterPassword.text.toString()
    private val confirmPassword: String
        get() = binding.confirmPasswordLayout.editText?.text.toString()


    init {
        TrainingApp.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener {
            registrationMode()
        }

        binding.changeModeButton.setOnClickListener {
            presenter.requestChangeMode()
        }

    }

    private fun registrationMode() {
        when (mode) {
            AuthMode.REGISTRATION -> {
                presenter.signUp(userEmail, userPassword, confirmPassword)
            }

            AuthMode.LOGIN -> {
                presenter.signIn(userEmail, userPassword)
            }
        }
    }


    override fun showViewProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }


    override fun hideViewProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun navigateToHome() {
        startActivity(Intent(this, TrainingsListActivity::class.java))
        finish()
    }


    override fun changeAuthMode() {
        val newMode = if (mode == AuthMode.LOGIN) AuthMode.REGISTRATION else AuthMode.LOGIN
        binding.apply {

            when (newMode) {
                AuthMode.LOGIN -> {
                    confirmPasswordLayout.visibility = View.GONE
                    signInButton.setText(R.string.sign_in_text)
                    confirmPasswordEditText.text?.clear()
                }

                AuthMode.REGISTRATION -> {
                    confirmPasswordLayout.visibility = View.VISIBLE
                    signInButton.setText(R.string.sign_up_text)
                }
            }


            mode = newMode
        }

    }
}