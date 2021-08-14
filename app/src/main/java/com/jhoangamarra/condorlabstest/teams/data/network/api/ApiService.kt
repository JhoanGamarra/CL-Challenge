package com.jhoangamarra.condorlabstest.teams.data.network.api

import com.google.gson.GsonBuilder
import com.jhoangamarra.condorlabstest.core.AppConstants
import com.jhoangamarra.condorlabstest.teams.data.network.model.NetworkResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("lookup_all_teams.php")
    suspend fun getTeams(
        @Query("id") leagueId: String,
    ): Response<NetworkResponse>


}

object RetrofitClient {

    val apiService by lazy {
        Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }

}