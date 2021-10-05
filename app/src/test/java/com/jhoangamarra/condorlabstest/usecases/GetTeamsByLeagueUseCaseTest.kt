package com.jhoangamarra.condorlabstest.usecases

import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.data.repository.ListTeamsRepositoryImpl
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.domain.repository.ListTeamsRepository
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeague
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeagueImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetTeamsByLeagueUseCaseTest {

    private val listTeamRepository = mockk<ListTeamsRepositoryImpl>()

    private lateinit var getTeamsByLeague: GetTeamsByLeague

    private val leagueId = "4335"
    private val notExistingLeagueId = "000"


    private val teamDomainModel = mockk<TeamDomainModel>()
    private val responseData = listOf(teamDomainModel)

    private val resultSuccessStatus = ResultStatus.Success(responseData)

    private val emptySuccessStatus = ResultStatus.Success(emptyList<TeamDomainModel>())


    @Before
    fun setup() {
        getTeamsByLeague = GetTeamsByLeagueImpl(listTeamRepository)
    }

    @Test
    fun `given an existing leagueId when invoke method is called then should return a Success of ResultStatus with a TeamDomainModel list`() {

        coEvery {
            listTeamRepository.getTeamsByLeague(leagueId)

        } answers {
            resultSuccessStatus
        }

        val getTeamsByLeagueResponse = runBlocking {
            getTeamsByLeague.invoke(leagueId)
        }

        Assert.assertEquals(resultSuccessStatus, getTeamsByLeagueResponse)

        coVerify(exactly = 1) {
            listTeamRepository.getTeamsByLeague(leagueId)
        }

    }

    @Test
    fun `given a not existing leagueId when invoke method is called then should return a Success of ResultStatus with a empty list of TeamDomainModel`() {

        coEvery {
            listTeamRepository.getTeamsByLeague(notExistingLeagueId)

        } answers {
            emptySuccessStatus
        }

        val getTeamsByLeagueResponse = runBlocking {
            getTeamsByLeague.invoke(notExistingLeagueId)
        }

        Assert.assertEquals(emptySuccessStatus, getTeamsByLeagueResponse)

        coVerify(exactly = 1) {
            listTeamRepository.getTeamsByLeague(notExistingLeagueId)
        }

    }


}