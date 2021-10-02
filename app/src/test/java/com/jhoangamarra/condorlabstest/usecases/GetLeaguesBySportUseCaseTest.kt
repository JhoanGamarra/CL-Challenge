package com.jhoangamarra.condorlabstest.usecases

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.repository.ListLeagueRepositoryImpl
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySport
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySportImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetLeaguesBySportUseCaseTest {

    private val listLeagueRepository = mockk<ListLeagueRepositoryImpl>()
    private val sportId = "Soccer"
    private val notExistingSportId = "0000"
    private val leagueDomainModel = mockk<LeagueDomainModel>()
    private val teamDomainModelList = listOf(leagueDomainModel)
    private val successResultStatus = ResultStatus.Success(teamDomainModelList)
    private val emptyResultStatus = ResultStatus.Success(emptyList<LeagueDomainModel>())
    private lateinit var getLeaguesBySport: GetLeaguesBySport


    @Before
    fun setup() {
        getLeaguesBySport = GetLeaguesBySportImpl(listLeagueRepository)
    }

    @Test
    fun `given an existing sportId when invoke method is called then should return a Success into ResultStatus with a leagues by sport list`() {

        coEvery {
            listLeagueRepository.getLeaguesBySport(sportId)
        } answers {
            successResultStatus
        }

        val expectResultStatus = runBlocking {
            getLeaguesBySport.invoke(sportId)
        }
        Assert.assertEquals(successResultStatus, expectResultStatus)

        coVerify {
            listLeagueRepository.getLeaguesBySport(sportId)
        }
    }

    @Test
    fun `given a not existing sportId when invoke method is called then should return a Success into ResultStatus with an empty list`() {

        coEvery {
            listLeagueRepository.getLeaguesBySport(notExistingSportId)
        } answers {
            emptyResultStatus
        }

        val expectResultStatus = runBlocking {
            getLeaguesBySport.invoke(notExistingSportId)
        }

        Assert.assertEquals(emptyResultStatus, expectResultStatus)

        coVerify {
            listLeagueRepository.getLeaguesBySport(notExistingSportId)
        }

    }


}