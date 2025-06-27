package com.example.trainingapp.presentation.signin

import android.os.Bundle
import android.view.View
import com.example.trainingapp.R
import com.example.trainingapp.data.AuthMode
import com.example.trainingapp.databinding.ActivitySignInBinding
import com.example.trainingapp.domain.di.modules.PresenterFactory
import com.example.trainingapp.presentation.base.BaseActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class AuthorizationActivity : BaseActivity(), AuthorizationView {

    private lateinit var binding: ActivitySignInBinding
    //private val presenter by moxyPresenter { AuthorizationPresenter() }

    @Inject
    lateinit var presenterFactory: PresenterFactory

    private val presenter by moxyPresenter { presenterFactory.createAuthorizationPresenter() }
    private var mode = AuthMode.LOGIN


    private val userEmail: String
        get() = binding.emailEditText.text.toString()
    private val userPassword: String
        get() = binding.enterPassword.text.toString()
    private val confirmPassword: String
        get() = binding.passwordLayout.editText?.text.toString()


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
        binding.emailEditText.visibility = View.GONE
        binding.passwordEditText.visibility = View.GONE
    }


    override fun hideViewProgress() {
        binding.progressBar.visibility = View.GONE
        binding.emailEditText.visibility = View.VISIBLE
        binding.passwordEditText.visibility = View.VISIBLE
        binding.emailEditText.visibility = View.VISIBLE
    }

    override fun navigateToHome() {
        TODO("Not yet implemented")
    }


    override fun changeAuthMode() {
        val newMode = if (mode == AuthMode.LOGIN) AuthMode.REGISTRATION else AuthMode.LOGIN
        binding.apply {

            when (newMode) {
                AuthMode.LOGIN -> {
                        passwordLayout.visibility = View.VISIBLE
                        signInButton.setText(R.string.sign_in_text)
                        passwordEditText.visibility = View.GONE
                }

                AuthMode.REGISTRATION -> {
                    passwordLayout.visibility = View.VISIBLE
                    signInButton.setText(R.string.sign_up_text)
                    presenter.signUp(userEmail, userPassword, confirmPassword)
                }
            }


            mode = newMode
        }




    }


}