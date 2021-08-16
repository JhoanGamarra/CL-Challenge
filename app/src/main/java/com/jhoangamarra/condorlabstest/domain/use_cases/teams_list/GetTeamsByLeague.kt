package com.jhoangamarra.condorlabstest.domain.use_cases.teams_list

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel

interface GetTeamsByLeague {

    suspend fun invoke(leagueId: String): ResultStatus<List<TeamDomainModel>>

}