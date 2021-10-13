package com.jhoangamarra.condorlabstest.datasources.local

import com.jhoangamarra.condorlabstest.data.local.dao.TeamDao
import com.jhoangamarra.condorlabstest.data.local.model.TeamEntity
import com.jhoangamarra.condorlabstest.data.local.source.LocalTeamDataSource
import com.jhoangamarra.condorlabstest.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.data.mappers.toEntityModel
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalTeamDataSourceTest {

    private val mockTeamDao = mockk<TeamDao>(relaxed = true)



    private val leagueId = "123"
    private val noExistingLeagueId = "000"

    private val mockTeamEntity =
        TeamEntity(
            idTeam = "idTeam",
            idLeague = "idLeague",
            strAlternate = "nameAlternate",
            intFormedYear = "2011",
            strTeam = "TeamName",
            strStadium = "stadum",
            strWebsite = "eeww.",
            strFacebook = "facebook",
            strTwitter = "dsdsd",
            strInstagram = "inind",
            strYoutube = "dkdfdf",
            strDescriptionEN = "dddesscripcio",
            strDescriptionES = "dessdd",
            strTeamBadge = "team",
            strTeamJersey = "ssdeef"
        )

    private val mockTeamDomainModel =
        TeamDomainModel(
            idTeam = "idTeam",
            idLeague = "idLeague",
            strAlternate = "nameAlternate",
            intFormedYear = "2011",
            strTeam = "TeamName",
            strStadium = "stadum",
            strWebsite = "eeww.",
            strFacebook = "facebook",
            strTwitter = "dsdsd",
            strInstagram = "inind",
            strYoutube = "dkdfdf",
            strDescriptionEN = "dddesscripcio",
            strDescriptionES = "dessdd",
            strTeamBadge = "team",
            strTeamJersey = "ssdeef"
        )

    private val mockTeamEntityList = listOf(mockTeamEntity)
    private val emptyListResponse = emptyList<TeamEntity>()


    private lateinit var localTeamDataSource: LocalTeamDataSource

    @Before
    fun setup() {
        localTeamDataSource = LocalTeamDataSource(mockTeamDao)
    }

    @Test
    fun `given an existing leagueId when getTeams is called then should return a teams list by league`() {

        coEvery {
            mockTeamDao.getTeams(leagueId)
        } answers {
            mockTeamEntityList
        }

        val getTeamsResponse = runBlocking { localTeamDataSource.getTeams(leagueId) }


        Assert.assertEquals(mockTeamEntityList.map { it.toDomainModel() }, getTeamsResponse)

        coVerify {
            mockTeamDao.getTeams(leagueId)
        }

    }


    @Test
    fun `given a not existing leagueId when getTeams is called then should return an empty list`() {

        coEvery {
            mockTeamDao.getTeams(noExistingLeagueId)
        } answers {
            emptyListResponse
        }

        val getTeamsResponse = runBlocking { localTeamDataSource.getTeams(noExistingLeagueId) }


        Assert.assertEquals(emptyListResponse.map { it.toDomainModel() }, getTeamsResponse)

        coVerify {
            mockTeamDao.getTeams(noExistingLeagueId)
        }

    }

    @Test
    fun `given a TeamDomainModel when saveTeam method is called then should call saveTeam of teamDao`() {


        coEvery {
            mockTeamDao.saveTeam(mockTeamDomainModel.toEntityModel())
        } answers {
        }

        runBlocking {
            localTeamDataSource.saveTeam(mockTeamDomainModel)
        }


        coEvery { mockTeamDao.saveTeam(mockTeamDomainModel.toEntityModel()) }

    }


}