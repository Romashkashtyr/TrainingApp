package com.example.core.repository


interface CheckAuthRepositoryCore {

    fun signOut()

    fun isUserLoggedIn(): Boolean

    fun setUserLoggedIn(isLoggedIn: Boolean)

}