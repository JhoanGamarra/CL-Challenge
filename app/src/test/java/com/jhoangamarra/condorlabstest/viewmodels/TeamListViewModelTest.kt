package com.jhoangamarra.condorlabstest.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jhoangamarra.condorlabstest.core.AppConstants
import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySport
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeague
import com.jhoangamarra.condorlabstest.presentation.models.LeagueModelView
import com.jhoangamarra.condorlabstest.presentation.models.TeamModelView
import com.jhoangamarra.condorlabstest.presentation.ui.teams_list.TeamListViewModel
import com.jhoangamarra.condorlabstest.utils.MainCoroutineRule
import com.jhoangamarra.condorlabstest.utils.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class TeamListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val getTeamsByLeague = mockk<GetTeamsByLeague>()
    private val getLeaguesBySport = mockk<GetLeaguesBySport>()
    private lateinit var teamListViewModel: TeamListViewModel
    private val teamObserver: Observer<List<TeamModelView>> = mock()
    private val leagueObserver: Observer<List<LeagueModelView>> = mock()
    private val leagueId = AppConstants.LEAGUE_ID
    private val sport = AppConstants.SPORT

    private val teamModelView = TeamModelView(
        "id", leagueId, "all", "year",
        "team", "s", "s", "s", "w", "i",
        "y", "des", "des", "t", "je"
    )
    private val teamDomainModel = TeamDomainModel(
        "id", leagueId, "all", "year",
        "team", "s", "s", "s", "w", "i",
        "y", "des", "des", "t", "je"
    )
    private val leagueDomainModel = LeagueDomainModel(
        "id", "name",
        "alternate",
        sport
    )
    private val leagueModelView = LeagueModelView(
        "id", "name",
    )
    private val teamsModelViewList = listOf(teamModelView)
    private val teamModelViewEmptyList =  emptyList<TeamModelView>()
    private val teamDomainModelEmptyList =  emptyList<TeamDomainModel>()

    private val teamsDomainModelList = listOf(teamDomainModel)
    private val leagueDomainModelList = listOf(leagueDomainModel)

    private val teamDomainList = mutableListOf<List<TeamModelView>>()



    init {
        coEvery { getTeamsByLeague.invoke(leagueId) } answers {
            ResultStatus.Success(
                teamsDomainModelList
            )
        }

        coEvery { getLeaguesBySport.invoke(AppConstants.SPORT) } answers {
            ResultStatus.Success(
                leagueDomainModelList
            )
        }
    }

    @Before
    fun setup() {

        teamListViewModel = TeamListViewModel(getTeamsByLeague, getLeaguesBySport)
        //teamListViewModel.teams.observeForever(Observer { list -> teamDomainList.add(list)})
        teamListViewModel.teams.observeForever(teamObserver)
        teamListViewModel.leagues.observeForever(leagueObserver)
        //teamListViewModel.getTeams(leagueId)

    }

    /*@After
    fun finish(){
        teamDomainList.clear()
    }*/


    /*@Test
    fun `given a leagueId and ResultStatus is Success and data is not null when getTeams is called then should return a Success ResultStatus with TeamDomainModel List`() {

        Dispatchers.setMain(TestCoroutineDispatcher())
        val expectedTeams = listOf(teamModelView)
        runBlockingTest {

        }

        Assert.assertEquals(4,teamDomainList.size)

        coVerify(exactly = 1) { getTeamsByLeague.invoke(leagueId) }
    }*/

    @Test
    fun `given a leagueId and ResultStatus is Success and data is not null when getTeams is only called in the init then should return a Success ResultStatus with TeamDomainModel List`() {

        val expectedTeams = listOf(teamModelView)

        val captor = ArgumentCaptor.forClass(listOf<TeamModelView>().javaClass)
        captor.run {
            verify(teamObserver, times(1)).onChanged(capture())
            Assert.assertEquals(expectedTeams, value)
        }

        coVerify(exactly = 1) { getTeamsByLeague.invoke(leagueId) }
    }

    @Test
    fun `given a not existing leagueId and ResultStatus is Success and data is null when getTeams is called then should return a Success ResultStatus with empty list`() {

        val expectedTeams = teamModelViewEmptyList
        coEvery { getTeamsByLeague.invoke("000") } answers {
            ResultStatus.Success(
                teamDomainModelEmptyList
            )
        }

        teamListViewModel.getTeams("000")

        val captor = ArgumentCaptor.forClass(listOf<TeamModelView>().javaClass)
        captor.run {
            verify(teamObserver, times(3)).onChanged(capture())
            Assert.assertEquals(expectedTeams, value)
        }

        coVerify(exactly = 1) { getTeamsByLeague.invoke(leagueId) }
    }

    @Test
    fun `given an existing sport and ResultStatus is Success when getLeaguesBySport is called then should return a ResultStatus Success with LeaguesDomainModel list`() {

        val expectedLeagues = listOf(leagueModelView)

        teamListViewModel.getLeaguesBySport(sport)

        val captor = ArgumentCaptor.forClass(listOf<LeagueModelView>().javaClass)
        captor.run {
            verify(leagueObserver, times(2)).onChanged(capture())
            Assert.assertEquals(expectedLeagues, value)
        }

        coVerify(exactly = 2) { getLeaguesBySport.invoke(sport) }

    }

    @Test
    fun `given a not existing sport and ResultStatus is Success when getLeaguesBySport is called then should return a ResultStatus Success with empty list`() {

        val expectedLeagues = emptyList<LeagueModelView>()

        coEvery { getLeaguesBySport.invoke("sss") } coAnswers { ResultStatus.Success(emptyList<LeagueDomainModel>())}

        teamListViewModel.getLeaguesBySport("sss")

        val captor = ArgumentCaptor.forClass(listOf<LeagueModelView>().javaClass)
        captor.run {
            verify(leagueObserver, times(2)).onChanged(capture())
            Assert.assertEquals(expectedLeagues, value)
        }

        coVerify(exactly = 1) { getLeaguesBySport.invoke(sport) }

    }

    @Test
    fun `given an existing sport and ResultStatus is Success when getLeaguesBySport is called in the init then should return a ResultStatus Success with LeaguesDomainModel list`() {

        val expectedLeagues = listOf(leagueModelView)

        val captor = ArgumentCaptor.forClass(listOf<LeagueModelView>().javaClass)
        captor.run {
            verify(leagueObserver, times(1)).onChanged(capture())
            Assert.assertEquals(expectedLeagues, value)
        }

        coVerify(exactly = 1) { getLeaguesBySport.invoke(sport) }

    }


}