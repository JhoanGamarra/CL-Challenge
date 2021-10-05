package com.jhoangamarra.condorlabstest.repositories

import com.jhoangamarra.condorlabstest.core.ConnectionCheck
import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.local.source.LocalTeamDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteTeamDataSource
import com.jhoangamarra.condorlabstest.data.repository.ListTeamsRepositoryImpl
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.domain.repository.ListTeamsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ListTeamsRepositoryTest {


    private val localTeamDataSource = mockk<LocalTeamDataSource>(relaxed = true)
    private val remoteTeamDataSource = mockk<RemoteTeamDataSource>()
    private val connectionCheck = mockk<ConnectionCheck>()
    private lateinit var listTeamsRepository: ListTeamsRepository
    private val leagueId = "4335"
    private val notExistingLeagueId = "0000"
    private val teamMock = mockk<TeamDomainModel>()
    private val teamsList = listOf(teamMock)
    private val teamsEmptyList = emptyList<TeamDomainModel>()
    private val successResultStatus = ResultStatus.Success(teamsList)
    private val successResultStatusEmptyList = ResultStatus.Success(teamsEmptyList)
    private val failureResultStatus = ResultStatus.Failure(Exception())


    @Before
    fun setup() {
        listTeamsRepository =
            ListTeamsRepositoryImpl(localTeamDataSource, remoteTeamDataSource, connectionCheck)
    }

    @Test
    fun `given an available internet connection and Failure ResultStatus when getTeamsByLeague is called then should return a ResultStatus Failure with Exception`() {

        coEvery {
            connectionCheck.isConnectionAvailable()
        } answers {
            true
        }

        coEvery {
            remoteTeamDataSource.getTeamsByLeague(leagueId)
        } answers {
            failureResultStatus
        }

        val getTeamsByLeagueResponse =
            runBlocking { listTeamsRepository.getTeamsByLeague(leagueId) }

        Assert.assertEquals(failureResultStatus, getTeamsByLeagueResponse)

        coVerify(inverse = true) { localTeamDataSource.saveTeam(teamMock) }
        coVerify(inverse = true) { localTeamDataSource.getTeams(leagueId) }
        coVerify(exactly = 1) { remoteTeamDataSource.getTeamsByLeague(leagueId) }
    }

    @Test
    fun `given an available internet connection and Success ResultStatus when getTeamsByLeagues is called then should return a ResultStatus Success with a leagues list`() {

        coEvery {
            connectionCheck.isConnectionAvailable()
        } answers {
            true
        }

        coEvery {
            remoteTeamDataSource.getTeamsByLeague(leagueId)
        } answers {
            successResultStatus
        }

        coEvery {
            localTeamDataSource.getTeams(leagueId)
        } answers {
            teamsList
        }

        val getTeamsByLeagueResponse = runBlocking {
            listTeamsRepository.getTeamsByLeague(leagueId)
        }

        Assert.assertEquals(ResultStatus.Success(teamsList), getTeamsByLeagueResponse)

        coVerify(exactly = 1) { remoteTeamDataSource.getTeamsByLeague(leagueId) }
        coVerify(exactly = 1) { localTeamDataSource.getTeams(leagueId) }
        coVerify(exactly = 1) { localTeamDataSource.saveTeam(teamMock) }

    }

    @Test
    fun `given an unavailable internet connection and Success ResultStatus when getTeamsByLeagues is called then should return a Success ResultStatus with empty list `() {

        coEvery { connectionCheck.isConnectionAvailable() } answers { false }
        coEvery { localTeamDataSource.getTeams(leagueId) } answers { teamsEmptyList }

        val getTeamsByLeaguesResponse = runBlocking {
            listTeamsRepository.getTeamsByLeague(leagueId)
        }

        Assert.assertEquals(ResultStatus.Success(teamsEmptyList), getTeamsByLeaguesResponse)

        coVerify(exactly = 1) { localTeamDataSource.getTeams(leagueId) }
        coVerify(inverse = true) { remoteTeamDataSource.getTeamsByLeague(leagueId) }
        coVerify(inverse = true) { localTeamDataSource.saveTeam(teamMock) }

    }

    @Test
    fun `given not existing leagueId and an available internet connection and Success ResultStatus when getTeamsByLeagues is called then should return a Success ResultStatus with empty list `() {

        coEvery { connectionCheck.isConnectionAvailable() } answers { true }
        coEvery { remoteTeamDataSource.getTeamsByLeague(notExistingLeagueId) } answers { successResultStatusEmptyList }
        coEvery { localTeamDataSource.getTeams(notExistingLeagueId) } answers { teamsEmptyList }

        val getTeamsByLeaguesResponse = runBlocking {
            listTeamsRepository.getTeamsByLeague(notExistingLeagueId)
        }

        Assert.assertEquals(ResultStatus.Success(teamsEmptyList), getTeamsByLeaguesResponse)

        coVerify(exactly = 1) { localTeamDataSource.getTeams(notExistingLeagueId) }
        coVerify(exactly = 1) { remoteTeamDataSource.getTeamsByLeague(notExistingLeagueId) }
        coVerify(inverse = true) { localTeamDataSource.saveTeam(teamMock) }

    }


}