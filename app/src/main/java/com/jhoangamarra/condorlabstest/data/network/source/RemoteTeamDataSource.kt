package com.jhoangamarra.condorlabstest.data.network.source

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.data.network.api.ApiService
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel

class RemoteTeamDataSource(private val apiService: ApiService) {


    suspend fun getTeamsByLeague(leagueId : String) : ResultStatus<List<TeamDomainModel>> {

        return try {
            val response = apiService.getTeamsByLeague(leagueId)

            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.teams.map{ it.toDomainModel() }
                ResultStatus.Success(data)
            }else{
                ResultStatus.Failure(Exception("hubo un error ${response.message()}"))
            }

        } catch (e: Exception) {
            ResultStatus.Failure(e)
        }
    }


}