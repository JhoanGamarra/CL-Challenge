package com.jhoangamarra.condorlabstest.data.network.source

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.data.network.api.ApiService
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel

class RemoteLeagueDataSource(private val apiService: ApiService) {


    suspend fun getLeaguesBySport(sport: String): ResultStatus<List<LeagueDomainModel>> {

        return try {
            val response = apiService.getLeagues()

            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.leagues.filter {
                    it.strSport == sport
                }.map {
                    it.toDomainModel()
                }
                ResultStatus.Success(data)
            } else {
                ResultStatus.Failure(Exception("hubo un error ${response.message()}"))
            }

        } catch (e: Exception) {
            ResultStatus.Failure(e)
        }
    }

}