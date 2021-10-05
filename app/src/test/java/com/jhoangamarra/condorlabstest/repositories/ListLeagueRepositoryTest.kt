package com.jhoangamarra.condorlabstest.repositories

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.core.ConnectionCheck
import com.jhoangamarra.condorlabstest.data.local.source.LocalLeagueDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteLeagueDataSource
import com.jhoangamarra.condorlabstest.data.repository.ListLeagueRepositoryImpl
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.repository.ListLeagueRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ListLeagueRepositoryTest {

    private val localLeagueDataSource = mockk<LocalLeagueDataSource>(relaxed = true)
    private val remoteLeagueDataSource = mockk<RemoteLeagueDataSource>()
    private val connectionCheck = mockk<ConnectionCheck>()
    private val mockLeague = LeagueDomainModel("id", "name", "alternateName", "sport")
    private val sport = "Soccer"
    private val notExistingSport = "000"
    private lateinit var listLeagueRepository: ListLeagueRepository
    private val successResultList = listOf(mockLeague)
    private val successResultStatus = ResultStatus.Success(successResultList)
    private val leaguesEmptyList = emptyList<LeagueDomainModel>()
    private val successResultStatusEmptyList = ResultStatus.Success(leaguesEmptyList)
    private val failureResultStatus = ResultStatus.Failure(Exception())

    @Before
    fun setup() {
        listLeagueRepository =
            ListLeagueRepositoryImpl(localLeagueDataSource, remoteLeagueDataSource, connectionCheck)
    }

    @Test
    fun `given an available internet connection and ResultStatus is Success when getLeaguesBySport is called then should return a ResultStatus with leagues list`() {

        coEvery {
            connectionCheck.isConnectionAvailable()
        } answers {
            true
        }

        coEvery {
            localLeagueDataSource.getLeagues(sport)
        } answers {
            successResultList
        }

        val expectedValue = ResultStatus.Success(successResultList)

        coEvery {
            remoteLeagueDataSource.getLeaguesBySport(sport)
        } answers {
            successResultStatus
        }

        val getLeaguesBySportResponse = runBlocking {
            listLeagueRepository.getLeaguesBySport(sport)
        }

        Assert.assertEquals(expectedValue, getLeaguesBySportResponse)

        coVerify(exactly = 1) {
            localLeagueDataSource.saveLeague(mockLeague)
        }

    }

    @Test
    fun `given a sportId with unavailable internet connection when getLeaguesBySport is called then should return a leagues list of database without updated data of remote datasource`() {

        coEvery {
            connectionCheck.isConnectionAvailable()
        } answers {
            false
        }

        coEvery {
            localLeagueDataSource.getLeagues(sport)
        } answers {
            successResultList
        }

        val expectedValue = ResultStatus.Success(successResultList)

        val getLeaguesBySportResponse = runBlocking {
            listLeagueRepository.getLeaguesBySport(sport)
        }

        Assert.assertEquals(expectedValue, getLeaguesBySportResponse)


        coVerify(inverse = true) {
            localLeagueDataSource.saveLeague(mockLeague)
        }

        coVerify(inverse = true) {
            remoteLeagueDataSource.getLeaguesBySport(sport)
        }

        coVerify(exactly = 1) {
            localLeagueDataSource.getLeagues(sport)
        }

    }

    @Test
    fun `given a not existing sport and available internet connection when getLeaguesBySport is called then should return a emptyList`() {

        coEvery {
            connectionCheck.isConnectionAvailable()
        }answers {
            true
        }

        coEvery {
            localLeagueDataSource.getLeagues(notExistingSport)
        }answers {
            leaguesEmptyList
        }

        coEvery {
            remoteLeagueDataSource.getLeaguesBySport(notExistingSport)
        } answers {
            successResultStatusEmptyList
        }

        val getLeaguesBySportResponse = runBlocking {
            listLeagueRepository.getLeaguesBySport(notExistingSport)
        }

        Assert.assertEquals(successResultStatusEmptyList , getLeaguesBySportResponse)

        coVerify(exactly = 1) {
            localLeagueDataSource.getLeagues(notExistingSport)
        }

        coVerify(inverse = true) {
            localLeagueDataSource.saveLeague(mockLeague)
        }

    }

    @Test
    fun `given an available internet connection and ResultStatus Failure then should return a Failure with an Exception`(){

        coEvery {
            connectionCheck.isConnectionAvailable()
        }answers {
            true
        }

        coEvery {
            remoteLeagueDataSource.getLeaguesBySport(sport)
        }answers {
            failureResultStatus
        }

        val  getLeaguesBySportResponse = runBlocking {
            listLeagueRepository.getLeaguesBySport(sport)
        }

       Assert.assertEquals(failureResultStatus, getLeaguesBySportResponse)

        coVerify(inverse = true) {
            localLeagueDataSource.saveLeague(mockLeague)
        }

        coVerify(inverse = true) {
            localLeagueDataSource.getLeagues(sport)
        }

        coVerify(exactly = 1) {
            remoteLeagueDataSource.getLeaguesBySport(sport)
        }

    }

}
