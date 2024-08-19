package com.example.trainingapp.domain.usecases

import android.app.Application
import com.example.trainingapp.R
import com.example.trainingapp.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth


class FirebaseAuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : FirebaseAuthRepository {

    override  fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    R.string.sign_in_success
                } else {
                    R.string.sign_in_failure
                }
            }
        }
    }
    }


