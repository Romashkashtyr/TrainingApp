package com.example.core.data

import com.example.core.repository.AuthRepositoryCore
import com.example.core.structures.Status
import javax.inject.Inject

class AuthRepositoryCoreImpl @Inject constructor(
): AuthRepositoryCore {
    override suspend fun signIn(email: String, password: String): Status<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): Status<Boolean> {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun isUserLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setUserLoggedIn(isLoggedIn: Boolean) {
        TODO("Not yet implemented")
    }
}