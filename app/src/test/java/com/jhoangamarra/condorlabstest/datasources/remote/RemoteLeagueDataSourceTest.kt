package com.jhoangamarra.condorlabstest.datasources.remote

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.data.network.api.ApiService
import com.jhoangamarra.condorlabstest.data.network.model.league.LeagueNetworkModel
import com.jhoangamarra.condorlabstest.data.network.model.league.LeagueNetworkResponse
import com.jhoangamarra.condorlabstest.data.network.source.RemoteLeagueDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteTeamDataSource
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.function.ThrowingRunnable
import retrofit2.Response
import retrofit2.http.Body

class RemoteLeagueDataSourceTest {

    private val apiService = mockk<ApiService>(relaxed = true)
    private val sport = "id"
    private val notExistingSport = "000"
    private val leagueNetworkModelMock = LeagueNetworkModel(
        "idLeague", "league", "alternate", "id"
    )
    private val leagueNetworkModelList = listOf(leagueNetworkModelMock)
    private val leagueNetworkModelEmptyList = emptyList<LeagueNetworkModel>()
    private val leagueNetworkEmptyResponse = LeagueNetworkResponse(leagueNetworkModelEmptyList)
    private val leagueNetworkResponse = LeagueNetworkResponse(leagueNetworkModelList)
    private val responseData = Response.success(leagueNetworkResponse)
    private val emptyResponseData = Response.success(leagueNetworkEmptyResponse)
    private lateinit var remoteTeamDataSource: RemoteLeagueDataSource


    @Before
    fun setup() {
        remoteTeamDataSource = RemoteLeagueDataSource(apiService)
    }

     @Test
     fun `given sportId and response is not successfully and body us null when getLeaguesBySport is called then should return ResultStatus Failure with Exception`() {

         val myMock = mockk<Response<LeagueNetworkResponse>>()
         coEvery { myMock.isSuccessful } answers {false}
         coEvery { myMock.body() } answers {null}
         coEvery { myMock.message() } answers {"null"}
         coEvery { apiService.getLeagues() } answers { myMock}
         val exception = Exception("hubo un error ${myMock.message()}")
         val expectedResponse = ResultStatus.Failure(exception)

         val getLeaguesBySportResponse = runBlocking {
             remoteTeamDataSource.getLeaguesBySport(sport)
         }

         Assert.assertEquals(expectedResponse , getLeaguesBySportResponse)
         coVerify(exactly = 1) { apiService.getLeagues() }

     }

    @Test
    fun `given sportId and response is successfully and body is not null when getLeaguesBySport is called then should return a ResultStatus Success with leagues list`() {

        coEvery { apiService.getLeagues() } answers { responseData }

        val leagueDomainModel = LeagueDomainModel(
            "idLeague", "league", "alternate", "id"
        )

        val expectedValue = ResultStatus.Success(listOf(leagueDomainModel))

        val getLeaguesBySportResponse = runBlocking {
            remoteTeamDataSource.getLeaguesBySport(sport)
        }

        Assert.assertEquals(expectedValue, getLeaguesBySportResponse)

        coVerify(exactly = 1) { apiService.getLeagues() }

    }

    @Test
    fun `given error when getLeaguesBySport is called then should return a ResultStatus Failure  with Exception`() {

        val exception = Exception()
        coEvery { apiService.getLeagues() } throws exception

        val getLeaguesBySport = runBlocking {
            remoteTeamDataSource.getLeaguesBySport(sport)
        }

        Assert.assertEquals(ResultStatus.Failure(exception), getLeaguesBySport)
        coVerify(exactly = 1) { apiService.getLeagues() }

    }

    @Test
    fun `given a not existing sport when getLeaguesBySport is called then should return a ResultStatus Success with a empty list`() {


        coEvery { apiService.getLeagues() } answers { responseData }

       val expectedValue = ResultStatus.Success(emptyList<LeagueDomainModel>())

        val getTeamsByLeagueResponse = runBlocking {
            remoteTeamDataSource.getLeaguesBySport(notExistingSport)
        }

        Assert.assertEquals(expectedValue, getTeamsByLeagueResponse)

        coVerify(exactly = 1) { apiService.getLeagues() }

    }

}