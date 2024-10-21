package com.example.trainingapp.data

import com.example.trainingapp.domain.AuthStatus
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class ExceptionCatcher {
    suspend fun launchCatcher(job: suspend () -> AuthStatus): AuthStatus{
        try {
            job()
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
        }catch (e: FirebaseNetworkException){
            AuthStatus.NoNetwork("No Network: ${e.message}")
        } catch (e: FirebaseException) {
            // Общая ошибка сети или Firebase
            AuthStatus.NoNetwork("Network error: ${e.message}")
        } catch (e: Exception) {
            // Для других неизвестных ошибок
            AuthStatus.Failure("An unknown error occurred: ${e.message}")
        }
        return AuthStatus.Failure("Unknown Error")
    }
}