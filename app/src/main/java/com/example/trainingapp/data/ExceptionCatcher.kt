package com.example.trainingapp.data

import com.example.trainingapp.domain.Status
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class ExceptionCatcher {
    suspend fun <T> launchWithCatch(job: suspend () -> Status<T>): Status<T>{
        return try {
            job()
        } catch (e: FirebaseException){
            e.message.toString()
            Status.NoNetwork("NoNetwork") // Уточнить
        } catch (e: FirebaseAuthWeakPasswordException) {
            Status.Failure("Weak password")
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Status.Failure("Invalid email format")
        } catch (e: FirebaseAuthUserCollisionException) {
            Status.Failure("Email already in use")
        }catch (e: FirebaseNetworkException){
            Status.NoNetwork("No Network: ${e.message}")
        } catch (e: FirebaseException) {
            Status.NoNetwork("Network error: ${e.message}")
        } catch (e: Exception) {
            Status.Failure("An unknown error occurred: ${e.message}")
        }
    }
}