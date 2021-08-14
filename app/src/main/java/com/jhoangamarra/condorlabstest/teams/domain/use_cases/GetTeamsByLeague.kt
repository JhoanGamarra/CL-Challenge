package com.jhoangamarra.condorlabstest.teams.domain.use_cases

import com.jhoangamarra.condorlabstest.core.Result
import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel

interface GetTeamsByLeague {

    suspend fun invoke(leagueId: String): Result<List<TeamDomainModel>>

}