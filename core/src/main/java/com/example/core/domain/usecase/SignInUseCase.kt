package com.example.core.domain.usecase

import com.example.core.R
import com.example.core.repository.AuthRepository
import com.example.core.structures.Status
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Status<Boolean> {

        if(email.isBlank() || password.isBlank()) {
            return Status.Failure("Something went wrong")
        }

        return authRepository.signIn(email, password)
    }
}