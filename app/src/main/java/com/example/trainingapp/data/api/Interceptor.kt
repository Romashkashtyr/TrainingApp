package com.example.trainingapp.data.api

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class Interceptor @Inject constructor() : Interceptor, Authenticator {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request.newBuilder()
            .addHeader("", "").build()
//        when(chain.proceed(request)) {
//            400 -> {
//
//            }
//            401 -> {
//
//            }
//        }
        return chain.proceed(request)
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        var requestAvailable: Request? = null
        try {
            requestAvailable = response.request.newBuilder()
                .addHeader("", "")
                .build()
            return requestAvailable
        } catch (e: Exception) {
            "Error ${e.message}"
        }
        return null
    }
}