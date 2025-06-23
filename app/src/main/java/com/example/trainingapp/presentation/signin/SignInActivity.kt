package com.example.trainingapp.presentation.signin

import android.content.Context
import android.net.ConnectivityManager
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
        val newMode = if (mode == AuthMode.LOGIN) AuthMode.REGISTRATION else AuthMode.LOGIN
        val email = binding.emailEditText.text.toString()
        val password = binding.enterPassword.text.toString()
        val confirmPassword = binding.passwordLayout.editText.toString()
        binding.apply {
            //emailEditText.setText(R.string.type_your_email)
            //enterPassword.setText(R.string.type_your_password)

            when (newMode) {
                AuthMode.LOGIN -> {
                    if(isNetworkAvailable()) {
                        passwordLayout.visibility = View.VISIBLE
                        signInButton.setText(R.string.sign_in_text)
                    } else {
                        println("Is not available")
                    }

                }

                AuthMode.REGISTRATION -> {
                    passwordLayout.visibility = View.VISIBLE
                    signInButton.setText(R.string.sign_up_text)
                    presenter.signUp(email, password, confirmPassword)
                }
            }

            emailEditText.text?.clear()
            enterPassword.text?.clear()

            mode = newMode
        }




    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }
}