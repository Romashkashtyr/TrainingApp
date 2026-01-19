package com.example.core.domain.usecase

import com.example.core.repository.AuthRepository
import com.example.core.structures.Status
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String
    ): Status<Boolean> {

        if (email.isBlank() || password.isBlank()) {
            return Status.Failure("You entered nothing")
        }
        if(confirmPassword != password) {
            return Status.Failure("Passwords aren't equals")
        }
        if(password.length < 6) {
            return Status.Failure("Weak password")
        }
        return authRepository.signUp(email, password, confirmPassword)
    }
}