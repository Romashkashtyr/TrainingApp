package com.example.auth.domain.usecase

import com.example.auth.repository.AuthRepository
import com.example.core.repository.AuthRepositoryCore
import javax.inject.Inject

class CheckAuthStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Boolean {
        return authRepository.isUserLoggedIn()
    }
}