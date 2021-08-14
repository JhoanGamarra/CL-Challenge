package com.jhoangamarra.condorlabstest.teams.data.network.source

import com.jhoangamarra.condorlabstest.core.Result
import com.jhoangamarra.condorlabstest.teams.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.teams.data.network.api.ApiService
import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel

class RemoteTeamDataSource(private val apiService: ApiService) {


    suspend fun getTeams(leagueId : String) : Result<List<TeamDomainModel>> {

        return try {
            val response = apiService.getTeams(leagueId)

            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.teams.map{ it.toDomainModel() }
                Result.Success(data)
            }else{
                Result.Failure(Exception("hubo un error ${response.message()}"))
            }

        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

}