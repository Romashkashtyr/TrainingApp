package com.example.trainingapp.start.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.trainingapp.R
import com.example.trainingapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class SignInActivity : MvpAppCompatActivity(), MPVViewSignIn {

    private lateinit var binding: ActivitySignInBinding
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val presenter by moxyPresenter { SignInPresenter(firebaseAuth) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signInButton.setOnClickListener{
            val userEmail = binding.emailEditText.text.toString()
            val userPassword = binding.passET.text.toString()
            presenter.signIn(userEmail, userPassword)
            presenter.requireShowToast(R.string.sign_in_success.toString())

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
        binding.textView.visibility = View.GONE
        binding.passET.visibility = View.GONE
        binding.emailEditText.visibility = View.GONE
    }

    override fun hideViewProgress() {
        binding.progressBar.visibility = View.GONE
        binding.textView.visibility = View.VISIBLE
        binding.passET.visibility = View.VISIBLE
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