package com.jhoangamarra.condorlabstest.teams.domain.use_cases

import com.jhoangamarra.condorlabstest.core.Result
import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.teams.domain.repository.TeamRepository

class GetTeamsByLeagueImpl(private val repository: TeamRepository) : GetTeamsByLeague {

    override suspend fun invoke(leagueId : String): Result<List<TeamDomainModel>> = repository.getTeamsByLeague(leagueId)

}