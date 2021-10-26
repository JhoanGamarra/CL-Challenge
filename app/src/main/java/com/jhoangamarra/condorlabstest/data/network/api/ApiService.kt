package com.jhoangamarra.condorlabstest.data.network.api

import com.jhoangamarra.condorlabstest.data.network.model.league.LeagueNetworkResponse
import com.jhoangamarra.condorlabstest.data.network.model.team.TeamNetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("lookup_all_teams.php")
    suspend fun getTeamsByLeague(
        @Query("id") leagueId: String,
    ): Response<TeamNetworkResponse>

    @GET("all_leagues.php")
    suspend fun getLeagues(): Response<LeagueNetworkResponse>


}
