package com.example.trainingapp.domain.di

import com.example.trainingapp.data.Constants
import com.example.trainingapp.data.TrainingsRepositoryImpl
import com.example.trainingapp.data.api.NetworkService
import com.example.trainingapp.domain.repository.TrainingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface TrainingModule {


    @Binds
    fun bindTrainingRepository(impl: TrainingsRepositoryImpl): TrainingsRepository





//    @AppScope
//    @Binds
//    fun bindTrainingRepository(impl: TrainingsRepositoryImpl): TrainingsRepository
}