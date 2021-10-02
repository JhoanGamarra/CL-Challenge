package com.jhoangamarra.condorlabstest.datasources.local

import com.jhoangamarra.condorlabstest.data.local.dao.LeagueDao
import com.jhoangamarra.condorlabstest.data.local.model.LeagueEntity
import com.jhoangamarra.condorlabstest.data.local.source.LocalLeagueDataSource
import com.jhoangamarra.condorlabstest.data.mappers.toDomainModel
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalLeagueDataSourceTest {

    private val mockLeagueDao = mockk<LeagueDao>(relaxed = true)
    private val sport = "Soccer"
    private val noExistingSport = "000"
    private val mockLeagueDomainModel =
        LeagueDomainModel(
            "id",
            "name",
            "djdjd",
            "Socceer"
        )
    private val leaguesDomainModelList = listOf(mockLeagueDomainModel)

    private val mockLeagueEntity = LeagueEntity(
        "id",
        "name",
        "djdjd",
        "Socceer"
    )

    private val leagueEntityList = listOf(mockLeagueEntity)
    private val leagueEntityEmptyList : List<LeagueEntity> = emptyList()

    private lateinit var localLeagueDataSource: LocalLeagueDataSource


    @Before
    fun setup() {
        localLeagueDataSource = LocalLeagueDataSource(mockLeagueDao)
    }


    @Test
    fun `given an existing sport when getLeagues is called then should return a LeagueDomainModel List`() {

        coEvery {
            mockLeagueDao.getLeagues(sport)
        } answers {
            leagueEntityList
        }

        val getLeaguesResponse = runBlocking {
            localLeagueDataSource.getLeagues(sport)
        }

        Assert.assertEquals(leagueEntityList.map{it.toDomainModel()} , getLeaguesResponse)

        coVerify { mockLeagueDao.getLeagues(sport) }


    }

    @Test
    fun `given a not existing sport when getLeagues is called then should return a empty List`() {

        coEvery {
            mockLeagueDao.getLeagues(noExistingSport)
        } answers {
            leagueEntityEmptyList
        }

        val getLeaguesResponse : List<LeagueDomainModel> = runBlocking {
            localLeagueDataSource.getLeagues(noExistingSport)
        }

        Assert.assertEquals(leagueEntityEmptyList, getLeaguesResponse)


       coVerify { mockLeagueDao.getLeagues(noExistingSport) }


    }



}