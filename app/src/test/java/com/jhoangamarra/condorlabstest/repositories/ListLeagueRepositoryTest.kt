package com.jhoangamarra.condorlabstest.repositories

import com.jhoangamarra.condorlabstest.core.InternetCheck
import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.local.source.LocalLeagueDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteLeagueDataSource
import com.jhoangamarra.condorlabstest.data.repository.ListLeagueRepositoryImpl
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.repository.ListLeagueRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import java.net.ConnectException

class ListLeagueRepositoryTest {

    private val localLeagueDataSource = mockk<LocalLeagueDataSource>()
    private val remoteLeagueDataSource = mockk<RemoteLeagueDataSource>()
    private val mockLeague = mockk<LeagueDomainModel>()
    private val sport = "Soccer"


    private var mockInternetConnection = false


    private lateinit var listLeagueRepository: ListLeagueRepository



    private val successResultStatus = ResultStatus.Success(listOf(mockLeague))

    private val failureResultStatus = ResultStatus.Failure(ConnectException())


    @Before
    fun setup() {
        listLeagueRepository =
            ListLeagueRepositoryImpl(localLeagueDataSource, remoteLeagueDataSource)
    }

    @Test
    fun `given an available internet connection and ResultStatus is Success when getLeaguesBySport is called then should be call saveLeague method`() {

        coEvery {



        } answers {
            runBlocking {
                localLeagueDataSource.saveLeague(mockLeague)
            }
        }

        if (mockInternetConnection) {
            runBlocking {
                listLeagueRepository.getLeaguesBySport(sport)
            }
        }

        coVerify(exactly = 1) {
            localLeagueDataSource.saveLeague(mockLeague)
        }

    }

    @Test
    fun `given an available internet connection and ResultStatus is Failure when getLeaguesBySport is called then should return Failure of ResultStatus with TimeOutException `() {
        runBlocking {
            if (InternetCheck.isNetworkAvailable()) {
                if (true) {
                    coVerify(inverse = true) {
                        localLeagueDataSource.saveLeague(mockLeague)
                    }
                }

            }
        }
    }


}