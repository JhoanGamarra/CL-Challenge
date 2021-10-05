package com.jhoangamarra.condorlabstest.datasources.remote

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.data.network.api.ApiService
import com.jhoangamarra.condorlabstest.data.network.model.team.TeamNetworkModel
import com.jhoangamarra.condorlabstest.data.network.model.team.TeamNetworkResponse
import com.jhoangamarra.condorlabstest.data.network.source.RemoteTeamDataSource
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteTeamDataSourceTest {

    private val apiService = mockk<ApiService>()
    private val leagueId = "123"
    private val notExistingLeagueId = "000"
    private val teamDomainModel = TeamDomainModel(
        "id", "123", "all", "year",
        "team", "s", "s", "s", "w", "i",
        "y", "des", "des", "t", "je"
    )
    private val teamNetworkModel = TeamNetworkModel(
        "id", "123", "all", "year",
        "team", "s", "s", "s", "w", "i",
        "y", "des", "des", "t", "je"
    )
    private val teamNetworkModelList = listOf(teamNetworkModel)
    private val teamNetworkModelEmptyList = emptyList<TeamNetworkModel>()
    private val teamDomainModelEmptyList = emptyList<TeamDomainModel>()
    private val successTeamNetworkResponse = TeamNetworkResponse(teamNetworkModelList)
    private val emptyTeamNetworkResponse = TeamNetworkResponse(teamNetworkModelEmptyList)
    private val teamDomainModeList = listOf(teamDomainModel)
    private val successResponse = Response.success(successTeamNetworkResponse)
    private val emptyResponse = Response.success(emptyTeamNetworkResponse)

    private lateinit var remoteTeamDataSource: RemoteTeamDataSource

    @Before
    fun setup() {
        remoteTeamDataSource = RemoteTeamDataSource(apiService)
    }

    @Test
    fun `given an existing leagueId and Response is successfully when getTeamsByLeague is called then should return a ResultStatus Success with teams list`() {

        coEvery { apiService.getTeamsByLeague(leagueId) } answers { successResponse }
        val expectedValue =
            ResultStatus.Success(successResponse.body()!!.teams.map { it.toDomainModel() })
        //val expectedValue = ResultStatus.Success(teamDomainModeList)

        val getTeamsByLeagueResponse = runBlocking {
            remoteTeamDataSource.getTeamsByLeague(leagueId)
        }
        Assert.assertEquals(expectedValue, getTeamsByLeagueResponse)

        coVerify(exactly = 1) {
            apiService.getTeamsByLeague(leagueId)
        }
    }

    @Test
    fun `given a not existing leagueId and Response is Successfully when getTeamsByLeague is called then should return an empty list`() {

        coEvery { apiService.getTeamsByLeague(notExistingLeagueId) } answers {emptyResponse}

        val expectedValue = ResultStatus.Success(teamDomainModelEmptyList)

        val getTeamsByLeagueResponse = runBlocking {
            remoteTeamDataSource.getTeamsByLeague(notExistingLeagueId)
        }

        Assert.assertEquals(expectedValue, getTeamsByLeagueResponse)

        coVerify(exactly = 1) {
            apiService.getTeamsByLeague(notExistingLeagueId)
        }
    }

    @Test
    fun `given error when getTeamsByLeagueResponse is called then should return a ResultStatus Failure  with Exception`() {

        val exception = Exception()
        coEvery { apiService.getTeamsByLeague(leagueId) } throws exception

        val getTeamsByLeagueResponse = runBlocking {
            remoteTeamDataSource.getTeamsByLeague(leagueId)
        }

        Assert.assertEquals(ResultStatus.Failure(exception), getTeamsByLeagueResponse)
        coVerify(exactly = 1) { apiService.getTeamsByLeague(leagueId) }

    }


}