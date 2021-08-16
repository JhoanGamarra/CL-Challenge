package com.jhoangamarra.condorlabstest.domain.repository

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel

interface ListTeamsRepository {

    suspend fun getTeamsByLeague(leagueId : String) : ResultStatus<List<TeamDomainModel>>

}