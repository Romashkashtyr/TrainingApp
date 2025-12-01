package com.example.auth.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.auth.R
import com.example.auth.data.AuthMode
import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.databinding.ActivitySignInBinding
import com.example.auth.domain.di.PresenterFactory
import com.example.core.base.BaseActivity
import com.example.core.navigation.RouterHolder.router
import com.example.core.navigation.Screen
import com.example.trainingapp.TrainingApp
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class AuthorizationActivity : BaseActivity(), AuthorizationView {

    private lateinit var binding: ActivitySignInBinding

    @Inject
    lateinit var presenterFactory: PresenterFactory
    private val presenter by moxyPresenter { presenterFactory.createAuthorizationPresenter() }
    @Inject
    lateinit var authRepository: AuthRepositoryImpl

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

        if(authRepository.isUserLoggedIn()) {
            //startActivity(Intent(MainActivity.getInstance(this)))
            router?.navigateTo(Screen.Main(this))
            finish()
            return
        }


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
        //startActivity(Intent(TrainingsListActivity.getIntent(this)))
        router?.navigateTo(Screen.TrainingNav(this))
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
                    changeModeButton.setText(R.string.not_entered_yet)
                }

                AuthMode.REGISTRATION -> {
                    confirmPasswordLayout.visibility = View.VISIBLE
                    signInButton.setText(R.string.sign_up_text)
                }
            }


            mode = newMode
        }

    }

    companion object {
        fun getIntent(fromContext: Context) = Intent(fromContext, AuthorizationActivity::class.java)
    }

}