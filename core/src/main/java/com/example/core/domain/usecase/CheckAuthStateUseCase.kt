package com.example.core.domain.usecase

import com.example.core.repository.AuthRepository
import javax.inject.Inject

class CheckAuthStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Boolean {
        return authRepository.isUserLoggedIn()
    }
}