package com.jhoangamarra.condorlabstest.data.di

import com.google.gson.GsonBuilder
import com.jhoangamarra.condorlabstest.core.AppConstants
import com.jhoangamarra.condorlabstest.data.network.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    fun providesRetrofit() : Retrofit{
        val apiService by lazy {
            Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
        return apiService
    }

    @Provides
    fun providesApi(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

}