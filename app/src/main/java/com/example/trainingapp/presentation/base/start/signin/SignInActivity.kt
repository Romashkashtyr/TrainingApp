package com.example.trainingapp.presentation.base.start.signin

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import com.example.trainingapp.R
import com.example.trainingapp.data.AuthMode
import com.example.trainingapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class SignInActivity : MvpAppCompatActivity(), SignInView {

    private lateinit var binding: ActivitySignInBinding
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val presenter by moxyPresenter { SignInPresenter() }
    var mode = AuthMode.LOGIN
    val toEditable = Editable.Factory.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signInButton.setOnClickListener{
            val userEmail = binding.emailEditText.text.toString()
            val userPassword = binding.enterPassword.text.toString()
            presenter.signIn(userEmail, userPassword)
            presenter.requireShowToast(R.string.sign_in_success)
            CoroutineScope(Dispatchers.IO).launch {
                when(mode) {
                    AuthMode.REGISTRATION -> {
                        val confirmPassword = binding.passwordLayout.editText.toString()
                        presenter.signUp(userEmail, userPassword, confirmPassword)
                        presenter.requestChangeMode()
                    }
                    AuthMode.LOGIN -> {
                        presenter.signIn(userEmail, userPassword)
                    }
                }

            }

        }


        binding.signUpAction.setOnClickListener {
                binding.apply {
                    signInButton.text = getString(R.string.sign_up_text)
                    emailEditText.text = toEditable.newEditable(getString(R.string.type_your_email))
                    enterPassword.text =
                        toEditable.newEditable(getString(R.string.type_your_password))
                    passwordLayout.visibility = View.VISIBLE
                    passEditText.visibility = View.VISIBLE
                    CoroutineScope(Dispatchers.IO).launch {
                        val userEmail = binding.emailEditText.text.toString()
                        val userPassword = binding.passEditText.text.toString()
                        val confirmUserPassword = binding.passEditText.text.toString()
                        presenter.signUp(userEmail, userPassword, confirmUserPassword)
                    }

                }

        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            currentUser.reload()
        }

    }

    override fun showViewProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.emailEditText.visibility = View.GONE
        binding.passEditText.visibility = View.GONE
    }


    override fun hideViewProgress() {
        binding.progressBar.visibility = View.GONE
        binding.emailEditText.visibility = View.VISIBLE
        binding.passEditText.visibility = View.VISIBLE
        binding.emailEditText.visibility = View.VISIBLE
    }

    override fun navigateToHome() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun changeAuthMode() {
        val newMode = if(mode == AuthMode.LOGIN) AuthMode.LOGIN else AuthMode.REGISTRATION
        when(mode) {
            AuthMode.LOGIN -> {
                binding.signInButton.setText(R.string.sign_in_text)
            }
            AuthMode.REGISTRATION -> {
                binding.signInButton.setText(R.string.sign_up_text)
            }
        }
        mode = newMode
    }


    override fun showToast(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}