package com.example.auth.domain.usecase

import com.example.core.repository.CheckAuthRepositoryCore
import javax.inject.Inject

class CheckAuthStateUseCase @Inject constructor(
    private val checkAuthRepository: CheckAuthRepositoryCore
) {

    operator fun invoke(): Boolean {
        return checkAuthRepository.isUserLoggedIn()
    }
}