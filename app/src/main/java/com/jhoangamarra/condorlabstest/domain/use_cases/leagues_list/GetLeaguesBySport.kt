package com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel

interface GetLeaguesBySport {

     suspend fun invoke(sport : String) : ResultStatus<List<LeagueDomainModel>>

}