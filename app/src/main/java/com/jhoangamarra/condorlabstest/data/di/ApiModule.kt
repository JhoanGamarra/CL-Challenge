package com.jhoangamarra.condorlabstest.data.di

import com.google.gson.GsonBuilder
import com.jhoangamarra.condorlabstest.core.AppConstants
import com.jhoangamarra.condorlabstest.data.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun providesApi() : ApiService{
        val apiService by lazy {
            Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(ApiService::class.java)
        }
        return apiService
    }

}