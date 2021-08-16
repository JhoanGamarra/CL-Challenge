package com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.repository.ListLeagueRepository

class GetLeaguesBySportImpl(private val listLeagueRepository: ListLeagueRepository ):GetLeaguesBySport {

    override suspend fun invoke(sport: String): ResultStatus<List<LeagueDomainModel>> = listLeagueRepository.getLeaguesBySport(sport)

}