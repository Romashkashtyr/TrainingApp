package com.example.trainingapp.domain.di

import com.example.trainingapp.data.api.ApiSettings
import com.example.trainingapp.data.api.Interceptor
import com.example.trainingapp.data.api.NetworkService
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

interface NetworkModule {


    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)


    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .addInterceptor(Interceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): NetworkService =
        Retrofit.Builder()
            .baseUrl(ApiSettings.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(NetworkService::class.java)
}