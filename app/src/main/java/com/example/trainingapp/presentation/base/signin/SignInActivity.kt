package com.example.trainingapp.presentation.base.signin

import android.os.Bundle
import android.view.View
import com.example.trainingapp.R
import com.example.trainingapp.data.AuthMode
import com.example.trainingapp.databinding.ActivitySignInBinding
import com.example.trainingapp.presentation.base.BaseActivity
import moxy.ktx.moxyPresenter

class SignInActivity : BaseActivity(), SignInView {

    private lateinit var binding: ActivitySignInBinding
    private val presenter by moxyPresenter { SignInPresenter() }
    private var mode = AuthMode.LOGIN


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signInButton.setOnClickListener {
            val userEmail = binding.emailEditText.text.toString()
            val userPassword = binding.enterPassword.text.toString()
            presenter.signIn(userEmail, userPassword)
            when (mode) {
                AuthMode.REGISTRATION -> {
                    val confirmPassword = binding.passwordLayout.editText.toString()
                    presenter.signUp(userEmail, userPassword, confirmPassword)
                }

                AuthMode.LOGIN -> {
                    presenter.signIn(userEmail, userPassword)
                }
            }

        }

        binding.changeModeButton.setOnClickListener {
            presenter.requestChangeMode()
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
        binding.apply {
            emailEditText.setText(R.string.type_your_email)
            enterPassword.setText(R.string.type_your_password)
            val newMode = if (mode == AuthMode.LOGIN) AuthMode.LOGIN else AuthMode.REGISTRATION
            when (mode) {
                AuthMode.LOGIN -> {
                    binding.passwordLayout.visibility = View.VISIBLE
                    binding.signInButton.setText(R.string.sign_in_text)
                }

                AuthMode.REGISTRATION -> {
                    binding.passwordLayout.visibility = View.VISIBLE
                    binding.signInButton.setText(R.string.sign_up_text)
                }
            }
            mode = newMode
        }



    }
}