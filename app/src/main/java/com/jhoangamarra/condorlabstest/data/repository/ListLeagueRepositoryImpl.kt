package com.jhoangamarra.condorlabstest.data.repository

import com.jhoangamarra.condorlabstest.core.ConnectionCheck
import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.local.source.LocalLeagueDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteLeagueDataSource
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.repository.ListLeagueRepository

class ListLeagueRepositoryImpl(
    private val localLeagueDataSource: LocalLeagueDataSource,
    private val remoteLeagueDataSource: RemoteLeagueDataSource,
    private val connectionCheck: ConnectionCheck
) : ListLeagueRepository {
    override suspend fun getLeaguesBySport(sport: String): ResultStatus<List<LeagueDomainModel>> {

        if (connectionCheck.isConnectionAvailable()) {
            when (val result = remoteLeagueDataSource.getLeaguesBySport(sport)
            ) {
                is ResultStatus.Success -> result.data.forEach { league ->
                    localLeagueDataSource.saveLeague(league)
                }
                is ResultStatus.Failure -> return result
            }
        }

        return ResultStatus.Success(localLeagueDataSource.getLeagues(sport))

    }
}