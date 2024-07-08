package com.example.trainingapp.start

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.trainingapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import moxy.MvpAppCompatActivity

class SignInActivity : MvpAppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.button.setOnClickListener{
            val userEmail = binding.emailEt.text.toString()
            val userPassword = binding.passET.text.toString()

            if(userEmail.isNotEmpty() && userPassword.isNotEmpty()){
                binding.button.visibility = View.VISIBLE
                firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
            } else {
                Toast.makeText(this, "Enter a valid data!", Toast.LENGTH_SHORT).show()
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
}