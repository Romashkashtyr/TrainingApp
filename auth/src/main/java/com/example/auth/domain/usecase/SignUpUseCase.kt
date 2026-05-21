package com.example.auth.domain.usecase


import android.util.Log
import com.example.auth.repository.AuthRepository
import com.example.core.structures.Status
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String
    ): Status<Boolean> {

        Log.d("SIGNUP_USECASE", "email=$email")
        Log.d("SIGNUP_USECASE", "password=$password")
        Log.d("SIGNUP_USECASE", "confirm=$confirmPassword")

        if (email.isBlank() || password.isBlank()) {
            Log.d("SIGNUP_USECASE", "FAIL: empty fields")
            return Status.Failure("You entered nothing")
        }
        if (confirmPassword != password) {
            Log.d("SIGNUP_USECASE", "FAIL: passwords not equal")
            return Status.Failure("Passwords aren't equals")
        }
        if (password.length < 6) {
            Log.d("SIGNUP_USECASE", "FAIL: weak password")
            return Status.Failure("Weak password")
        }

        Log.d("SIGNUP_USECASE", "passing to repository")
        return authRepository.signUp(email, password, confirmPassword)
    }
}