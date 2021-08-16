package com.jhoangamarra.condorlabstest.data.local.source

import com.jhoangamarra.condorlabstest.data.local.dao.LeagueDao
import com.jhoangamarra.condorlabstest.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.data.mappers.toEntityModel
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel

class LocalLeagueDataSource(private val leagueDao: LeagueDao) {


    suspend fun getLeagues(sport : String): List<LeagueDomainModel> {
        return leagueDao.getLeagues(sport).map { it.toDomainModel() }
    }

    suspend fun saveLeague(league : LeagueDomainModel){
        leagueDao.saveLeague(league.toEntityModel())
    }


}