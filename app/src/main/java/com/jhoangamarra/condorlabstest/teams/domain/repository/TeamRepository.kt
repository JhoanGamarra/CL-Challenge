package com.jhoangamarra.condorlabstest.teams.domain.repository

import com.jhoangamarra.condorlabstest.core.Result
import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel

interface TeamRepository {

    suspend fun getTeamsByLeague(leagueId : String) : Result<List<TeamDomainModel>>

}