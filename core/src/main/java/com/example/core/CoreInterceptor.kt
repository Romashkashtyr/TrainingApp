package com.example.core

import android.util.Log
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class CoreInterceptor @Inject constructor() : Interceptor, Authenticator {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        Log.d("CoreInterceptor", "Interceptor сработал! Ключ начинается с: ${BuildConfig.TRAINING_API_KEY.take(10)}")
        val newRequest = originalRequest.newBuilder()
            .header("Accept" , "application/json")
            .header("x-api-key" , BuildConfig.TRAINING_API_KEY)
            .build()
        return chain.proceed(newRequest)
  // add header вместо head
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        var requestAvailable: Request? = null
        try {
            requestAvailable = response.request.newBuilder()
                //.addHeader("", "")
                .build()
            return requestAvailable
        } catch (e: Exception) {
            "Error ${e.message}"
        }
        return null
    }


}