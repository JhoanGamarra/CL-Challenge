package com.jhoangamarra.condorlabstest.teams.data.repository

import com.jhoangamarra.condorlabstest.core.InternetCheck
import com.jhoangamarra.condorlabstest.core.Result
import com.jhoangamarra.condorlabstest.teams.data.local.source.LocalTeamDataSource
import com.jhoangamarra.condorlabstest.teams.data.network.source.RemoteTeamDataSource
import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.teams.domain.repository.TeamRepository

class TeamRepositoryImpl(
    private val localTeamDataSource: LocalTeamDataSource,
    private val remoteTeamDataSource: RemoteTeamDataSource
) : TeamRepository {


    override suspend fun getTeamsByLeague(leagueId: String): Result<List<TeamDomainModel>> {

        if (InternetCheck.isNetworkAvailable()) {
            when (val result = remoteTeamDataSource.getTeams(leagueId)) {
                is Result.Success -> result.data.forEach { team -> localTeamDataSource.saveTeam(team) }
                is Result.Failure -> return result
            }
        }

        return Result.Success(localTeamDataSource.getTeams())

    }


}