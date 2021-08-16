package com.jhoangamarra.condorlabstest.data.local.source

import com.jhoangamarra.condorlabstest.data.local.dao.TeamDao
import com.jhoangamarra.condorlabstest.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.data.mappers.toEntityModel
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel

class LocalTeamDataSource(private val teamDao: TeamDao) {

    suspend fun getTeams(leagueId : String): List<TeamDomainModel> {
        return teamDao.getTeams(leagueId).map { it.toDomainModel() }
    }

    suspend fun saveTeam(team : TeamDomainModel){
        teamDao.saveTeam(team.toEntityModel())
    }

}