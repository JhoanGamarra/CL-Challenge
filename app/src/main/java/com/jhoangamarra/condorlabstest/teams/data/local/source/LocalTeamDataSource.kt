package com.jhoangamarra.condorlabstest.teams.data.local.source

import com.jhoangamarra.condorlabstest.teams.data.local.dao.TeamDao
import com.jhoangamarra.condorlabstest.teams.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.teams.data.mappers.toEntityModel
import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel

class LocalTeamDataSource(private val teamDao: TeamDao) {

    suspend fun getTeams(): List<TeamDomainModel> {
        return teamDao.getTeams().map { it.toDomainModel() }
    }

    suspend fun saveTeam(team : TeamDomainModel){
        teamDao.saveTeam(team.toEntityModel())
    }

}