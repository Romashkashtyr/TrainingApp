package com.example.trainingapp.data

import com.example.trainingapp.domain.repository.AuthRepository
import com.example.trainingapp.domain.AuthStatus
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signIn(email: String, password: String): AuthStatus {

        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if(result.user != null) {
                AuthStatus.Success("Success") // Уточнить
            } else {
                AuthStatus.Failure("Failure") // Уточнить
            }
        } catch (e: FirebaseException) {
            e.message.toString()
            AuthStatus.NoNetwork("NoNetwork") // Уточнить
        }
    }



    override suspend fun signUp(email: String, password: String, confirmPassword: String): AuthStatus {


        if (password != confirmPassword) {
            return AuthStatus.Failure("Passwords do not match")
        }

        return try {
            val resultSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (resultSignUp.user != null){
                AuthStatus.Success("Success") // Уточнить
            } else {
                AuthStatus.Failure("Failure") // Уточнить
            }
        } catch (e: FirebaseException){
            e.message.toString()
            AuthStatus.NoNetwork("NoNetwork") // Уточнить
        } catch (e: FirebaseAuthWeakPasswordException) {
        // Пароль слишком слабый
        AuthStatus.Failure("Weak password")

    } catch (e: FirebaseAuthInvalidCredentialsException) {
        // Неверный формат email
        AuthStatus.Failure("Invalid email format")

    } catch (e: FirebaseAuthUserCollisionException) {
        // Такой email уже зарегистрирован
        AuthStatus.Failure("Email already in use")

    } catch (e: FirebaseException) {
        // Общая ошибка сети или Firebase
        AuthStatus.NoNetwork("Network error: ${e.message}")

    } catch (e: Exception) {
        // Для других неизвестных ошибок
        AuthStatus.Failure("An unknown error occurred: ${e.message}")
    }

    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}


