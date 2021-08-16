package com.jhoangamarra.condorlabstest.data.repository

import com.jhoangamarra.condorlabstest.core.InternetCheck
import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.local.source.LocalTeamDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteTeamDataSource
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.domain.repository.ListTeamsRepository

class ListTeamsRepositoryImpl(
    private val localTeamDataSource: LocalTeamDataSource,
    private val remoteTeamDataSource: RemoteTeamDataSource
) : ListTeamsRepository {


    override suspend fun getTeamsByLeague(leagueId: String): ResultStatus<List<TeamDomainModel>> {

        if (InternetCheck.isNetworkAvailable()) {
            when (val result = remoteTeamDataSource.getTeamsByLeague(leagueId)) {
                is ResultStatus.Success -> result.data.forEach { team -> localTeamDataSource.saveTeam(team) }
                is ResultStatus.Failure -> return result
            }
        }

        return ResultStatus.Success(localTeamDataSource.getTeams(leagueId))

    }



}