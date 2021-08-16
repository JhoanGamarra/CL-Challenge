package com.jhoangamarra.condorlabstest.domain.repository

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel

interface ListLeagueRepository {

    suspend fun getLeaguesBySport(sport : String) : ResultStatus<List<LeagueDomainModel>>

}