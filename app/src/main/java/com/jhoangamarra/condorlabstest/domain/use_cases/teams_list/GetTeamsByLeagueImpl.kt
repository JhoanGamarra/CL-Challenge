package com.jhoangamarra.condorlabstest.domain.use_cases.teams_list

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.domain.repository.ListTeamsRepository

class GetTeamsByLeagueImpl(private val repository: ListTeamsRepository) : GetTeamsByLeague {

    override suspend fun invoke(leagueId : String): ResultStatus<List<TeamDomainModel>> = repository.getTeamsByLeague(leagueId)

}