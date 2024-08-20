package com.example.trainingapp.start.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.trainingapp.R
import com.example.trainingapp.databinding.ActivitySignInBinding
import com.example.trainingapp.databinding.ActivitySignUpBinding
import com.example.trainingapp.start.signin.MPVViewSignIn
import com.example.trainingapp.start.signin.SignInPresenter
import com.google.firebase.auth.FirebaseAuth
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class SignUpActivity : MvpAppCompatActivity(), ViewSignUp {

    private lateinit var binding: ActivitySignUpBinding
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val presenter by moxyPresenter { SignUpPresenter(firebaseAuth) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signUpButton.setOnClickListener{
            val userEmail = binding.emailEditText.text.toString()
            val userPassword = binding.passEditText.text.toString()
            val confirmUserPassword = binding.confirmEditText.text.toString()
            presenter.signUp(userEmail, userPassword, confirmUserPassword)
        }
    }



    override fun showViewProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.textView.visibility = View.GONE
        binding.passEditText.visibility = View.GONE
        binding.emailEditText.visibility = View.GONE
    }

    override fun hideViewProgress() {
        binding.progressBar.visibility = View.GONE
        binding.textView.visibility = View.VISIBLE
        binding.passEditText.visibility = View.VISIBLE
        binding.emailEditText.visibility = View.VISIBLE
    }

    override fun navigateToHome() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(message: String) {
        TODO("Not yet implemented")
    }
}