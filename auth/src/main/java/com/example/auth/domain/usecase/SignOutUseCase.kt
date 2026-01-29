package com.example.auth.domain.usecase

import com.example.core.repository.CheckAuthRepositoryCore
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val checkAuthRepositoryCore: CheckAuthRepositoryCore
) {
    operator fun invoke() = checkAuthRepositoryCore.signOut()
}